
var type = "${sessionScope.user.type}";

if (type == "1" && "${sessionScope.user.userId}" != "${room.createrId}") {
    type = "0";
}
console.log(type);
var state = "${room.teaching}";



var fatherWidth = $("#main").width();
var playBody = (fatherWidth) * 0.7;
var _width = playBody + ""; //播放器属性
var _height = playBody / 1.65;
var _stream = "${room.id}";
if (state == "true") {
    createFlash(_width, _height, _stream, _rtmpIp);
} else {
    createFlash(_width, _height, null, _rtmpIp);
}

$("#controlBar").css("width", fatherWidth - 45);
$("#playBody").css("width", playBody);
$("#CuPlayer").css("width", playBody);

/**
 *自适应聊天框的宽度
 */
var fWidth = $("#main").width();
var lWidth = $("#playBody").width();
$("#chatBox").css("width", fWidth - lWidth - 50 + 'px');
$("#chatBox").height($("#playBody").height() + "px");
console.log($("#playBody").height());

/**
 * 自适应头部信息
 */
var fatherWidth = $("#playBody").width();
$("#headContent").css("width", fatherWidth - 110 + 'px');


function createFlash(width, height, stream, rtmpIp) {
    console.log(stream, rtmpIp);
    var so = new SWFObject("/play/player.swf", "ply", width, height, "9", "#000000");
    so.addParam("allowfullscreen", "true");
    so.addParam("allowscriptaccess", "always");
    so.addParam("wmode", "opaque");
    so.addParam("quality", "high");
    so.addParam("salign", "lt");
    <!-- HTML代码参数/Begin -->
    so.addVariable("JcScpServer", rtmpIp); //您的rtmp流媒体服务器地址
    so.addVariable("JcScpVideoPath", stream); //流名称
    so.addVariable("JcScpImg", "/play/images/startpic.jpg"); //视频缩略图
    so.addVariable("JcScpFile", "/play/CuSunV4set.xml"); //配置文件
    <!-- HTML代码参数/End -->
    so.write("CuPlayer");
}

window.onresize = function () {
    var fatherWidth = $("#main").width();
    var playBody = fatherWidth * 0.7;
    var _width = playBody + ""; //播放器属性
    var _height = playBody / 1.65;

    /**
     * 设置基本框架
     *
     */
    $("#controlBar").css("width", fatherWidth - 45);
    $("#playBody").css("width", playBody);
    $("#CuPlayer").css("width", playBody);

    /**
     *自适应聊天框的宽度
     */
    var fWidth = $("#main").width();
    var lWidth = $("#playBody").width();
    $("#chatBox").css("width", fWidth - lWidth - 50 + 'px');

    /**
     * 自适应头部信息
     */
    var fatherWidth = $("#playBody").width();
    $("#headContent").css("width", fatherWidth - 110 + 'px');
    if (state == "true") {
        createFlash(_width, _height, _stream, _rtmpIp);
    } else {
        createFlash(_width, _height, null, _rtmpIp);
    }
}

if (state == "true") {
    $("#state").html("正在直播");

} else {
    $("#state").html("主播不在家");
}


if (type == "0" || type == "") {
    $("#controlBar").css("display", "none");
    $("#manageButton").css("display", "none");
} else if (type == "1") {
    $("#manageButton").css("display", "none");
} else if (type == "2") {
    $("#controlBar").css("display", "none");
}

function sendBarrage(danmakuId, nickName, content, userId) {
    var item = {
        // img: '/static/img/cute.png', //图片
        userId: userId,
        danmakuId: danmakuId,
        nickName: nickName,
        info: content, //文字
        href: '#', //链接
        close: false, //显示关闭按钮
        speed: 6, //延迟,单位秒,默认6
        bottom: 0, //距离底部高度,单位px,默认随机
        color: '#fff', //颜色,默认白色
        old_ie_color: '#000000', //ie低版兼容色,不能与网页背景相同,默认黑色
    }
    $('#CuPlayer').barrager(item);
}

var websocket = null;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://" + window.location.host + "/WebScoket/" + "${room.id}"); //房间号
}
else {
    layer.open({
        title: "信息",
        content: "Not support websocket",
    });
    // alert('Not support websocket');
}

//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function (event) {
    console.log("open");
};

//接收到消息的回调方法
websocket.onmessage = function () {
    var msg = JSON.parse(event.data);
    console.log(msg);
    if (msg.type == "chat") {
        chatMessage(msg.content.nickName, msg.content.content);
        sendBarrage(msg.content.id, msg.content.userName, msg.content.content, msg.content.userId);
    } else if (msg.type == 'showState') {
        if (msg.content.state == 0) {

            $("#state").html("主播不在家");
            createFlash(_width, _height, null, _rtmpIp);
            layer.open({
                title: "信息",
                content: "主播不在家咯",
            });
            // alert("主播不在家咯");
        }
    }

};

//连接关闭的回调方法
websocket.onclose = function () {
    console.log("close");
};

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
};


//关闭连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
$("#sendButton").click(function () {
    var str = $("#sendContent").val();
    $("#sendContent").val("");
    if (type == "") {
        layer.open({
            title: "信息",
            content: "请先登录",
        });
        $("#loginA").click();
        // alert("请先登录");
    } else if (str == "") {
        layer.open({
            title: "信息",
            content: "请不要发空信息",
        });
        // alert("请不要发空信息")
    } else {
        websocket.send(JSON.stringify(createChatMsg(str)));
    }

});


function createChatMsg(content) {
    var message = {
        type: "chat",
        content: {
            id: null,
            userId: "${sessionScope.user.userId}",
            roomId: "${room.id}",
            userName:"${sessionScope.user.username}",
            content: content
        }
    }
    return message;
}


function showState(state) {
    var message = {
        type: "showState",
        content: {
            state: state
        }
    }
    return message;
}