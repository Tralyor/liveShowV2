package org.liveshow.chat;

/**
 * Created by Cjn on 2017/11/27.
 */

import com.mysql.cj.xdevapi.JsonArray;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONObject;
import org.liveshow.chat.content.GetHttpSessionConfigurator;
import org.liveshow.entity.Tuser;
import org.liveshow.entity.UserClassMapping;
import org.liveshow.service.LearnRecordService;
import org.liveshow.service.UserClassMappingService;
import org.liveshow.service.UserService;
import org.liveshow.service.resolver.ResolverFactory;
import org.liveshow.util.SessionUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/WebScoket/{roomId}" , configurator = GetHttpSessionConfigurator.class)
public class WebScoket {
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的danmakuChat对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebScoket> webSocketSet = new CopyOnWriteArraySet<WebScoket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //标识
    private int roomId;
    
    private HttpSession httpSession;

    @Resource
    private ResolverFactory resolverFactory;
    @Resource
    private LearnRecordService learnRecordService;
    @Resource
    private UserClassMappingService userClassMappingService;
    @Resource
    private UserService userService;

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebScoket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebScoket.onlineCount--;
    }

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("roomId") int roomId , Session session,EndpointConfig config){
        this.session = session;
        this.roomId = roomId;
        this.httpSession =(HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        broadCast();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount() + "roomId:" + roomId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        resolveEnd();
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        broadCast();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * 
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("来自客户端的消息:" + message);
        resolverFactory.doAction(message,this);
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        resolveEnd();
        broadCast();
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public CopyOnWriteArraySet<WebScoket> getWebSocketSet() {
        return webSocketSet;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Session getSession() {
        return session;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    private void resolveEnd() {
        try {
            Tuser tuser = (Tuser) httpSession.getAttribute("user");
            if ( tuser != null ) {
                learnRecordService.updateByUserId(tuser.getUserId(), roomId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //为了老师能够史是看到这个房间内还有那些人是没有到的
    private void  broadCast() {
        List<UserClassMapping> res =  userClassMappingService.getClassUser(roomId);
        if (CollectionUtils.isEmpty(res) ) {
            return;
        }
        List<Tuser> tusers = new ArrayList<>();
        Set<String> allUid = new HashSet<>();
        List<String> offLine = new ArrayList<>();
        webSocketSet.forEach(val->{
            Tuser tuser = (Tuser) val.getHttpSession().getAttribute("user");
            if ( tuser != null && tuser.getUserId()!=null) {
                allUid.add(tuser.getUserId());
            }
        });

        res.forEach(item->{
           if (!allUid.contains(item.getUserId()) ){
               offLine.add(item.getUserId());
           }
        });

        offLine.forEach(item->{
            Tuser tmp = userService.queryUserByUserId(item);
            if ( tmp != null ) {
                Tuser tuserTmp = new Tuser();
                tuserTmp.setUserId(tmp.getUserId());
                tuserTmp.setUserName(tmp.getUserName());
                tusers.add(tuserTmp);
            }
        });
        Message<List<Tuser>> msg = new Message<>();
        msg.setType("notOnlineStudent");
        msg.setContent(tusers);
        webSocketSet.forEach(item->{
            if ( item.getRoomId() == getRoomId()) {
                try {
                    item.sendMessage(new JSONObject(msg).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        List<Tuser> tusers = new ArrayList<>();
        Tuser tuser = new Tuser();
        tuser.setUserName("!");
        tuser.setUserId("123");
        tusers.add(tuser);
        Message<List<Tuser>> msg = new Message<>();
        msg.setType("notOnlineStudent");
        msg.setContent(tusers);
        System.out.println();
        System.out.println(JSONObject.valueToString(msg));
    }
}

