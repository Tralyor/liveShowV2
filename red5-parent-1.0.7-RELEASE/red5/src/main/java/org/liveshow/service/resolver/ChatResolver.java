package org.liveshow.service.resolver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.liveshow.chat.Message;
import org.liveshow.chat.WebScoket;
import org.liveshow.chat.content.ChatContent;
import org.liveshow.entity.TeahRecor;
import org.liveshow.service.DanmakuService;
import org.liveshow.service.TeachRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Cjn on 2017/12/6.
 */
@Service
public class ChatResolver  implements ContentResolver{
    @Autowired
    private DanmakuService danmakuService;
    @Resource
    private TeachRecordService teachRecordService;

    @Override
    public void resolve(String msgJson, WebScoket webScoket) {
        Gson gson = new Gson();
        Type objectType = new TypeToken<Message<ChatContent>>(){}.getType();
        Message<ChatContent> message = gson.fromJson(msgJson,objectType);
        ChatContent chatContent = message.getContent();
        if ( chatContent == null || chatContent.getRoomId() == null  ) {
            return;
        }
        TeahRecor teahRecor = teachRecordService.getReMaxClassNum(chatContent.getRoomId());
        if ( teahRecor == null || teahRecor.getGmtEnd() != null || teahRecor.getId() == null) {
             return;
        }
        int res = danmakuService.addDanmaku(chatContent.getUserId(),teahRecor.getId(),chatContent.getContent());
        if (res != 0){
            chatContent.setId(res);
            for(WebScoket item:webScoket.getWebSocketSet()){
                try {
                    if (item.getRoomId() == chatContent.getRoomId()){
                        item.sendMessage(gson.toJson(message));
                    }

                } catch (IOException e) {
                    continue;
                }
            }
        }


    }
}
