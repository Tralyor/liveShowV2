<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${room.className}的直播间</title>
    <%@ include file="../common/resources.jsp" %>
    <link rel="stylesheet" href="/static/css/user/liveshow.css">
    <link rel="stylesheet" href="/static/barrage/static/css/style.css"/>
    <link rel="stylesheet" href="/static/barrage/dist/css/barrager.css">
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        a, a:hover, a:active, a:visited {
            text-decoration: none;
        }

        textarea {
            resize: none;
            overflow: hidden;
        }

        #careButton {
            cursor: pointer;
        }
    </style>

</head>
<body>
<script type="text/javascript" src="/play/js/swfobject.js"></script>


<jsp:include page="../common/topNav.jsp"></jsp:include>
<div id="main">
    <div id="controlBar" style="width: 100%;height: 60px;margin-bottom: 10px">
        <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
        <span>${room.id}</span>
        <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
        <button type="button" class="btn btn-info" id="sbt">开启</button>
        <button type="button" class="btn btn-warning" id="ebt">关闭</button>
    </div>
    <div id="playBody">
        <div id="playTitle">
            <div id="headPic">
                <img src="/static/img/logo/logo-0.png" alt="">
            </div>
            <div id="headContent">
                <div style="height: 32%;"><h4>${room.className}</h4></div>
                <div style="height: 30%;color: #838C9A">3333 <span class="glyphicon glyphicon-triangle-right"
                                                                   aria-hidden="true"></span> 333
                </div>
                <div style="height:20%;">${room.className} <span class="glyphicon glyphicon-fire" style="color: #FF7676"
                                                                 aria-hidden="true"></span> <span>todo</span>

                    <span>状态：</span><span id="state">正在直播</span>
                </div>
            </div>
        </div>
        <div class="video" id="CuPlayer">
            <b> <img src="/play/images/loading.gif"/> 网页视频播放器加载中，请稍后...</b>
        </div>
    </div>
    <div id="chatBox">
        <div id="chatView">
            <ul class="ulDanmaku">

            </ul>
        </div>
        <div id="chatSend">
            <textarea id="sendContent" placeholder="试着和大家聊天吧"></textarea>
            <div id="sendButton">发送</div>
        </div>
    </div>

    <div id="notif"
         style="border: solid 1px #E5E4E4; height: 150px;border-radius:1%;width: 80%; margin: 0 auto;margin-top: 15px">
        <h3 style="text-align: center">直播公告</h3>
        <div style="text-align: center;width: 80%;height: 80px;line-height:80px; margin: 0 auto">
            <span>${room.classIntro}</span>
        </div>
    </div>
</div>

<div id="camera" style="position:fixed;right:0;bottom:0;">
    <video id="video" width="250" height="250" controls>
    </video>
</div>

<%@ include file="../common/resources-foot.jsp" %>
<script type="text/javascript" src="/static/barrage/static/js/tinycolor-0.9.15.min.js"></script>
<script type="text/javascript" src="/static/barrage/dist/js/jquery.barrager.js"></script>
<script type="text/javascript" src="/static/js/config/rtmpConfig.js"></script>
<script>
    function getUserMedia(constraints, success, error) {
        if (navigator.mediaDevices.getUserMedia) {
            //最新的标准API
            navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
        } else if (navigator.webkitGetUserMedia) {
            //webkit核心浏览器
            navigator.webkitGetUserMedia(constraints,success, error)
        } else if (navigator.mozGetUserMedia) {
            //firfox浏览器
            navigator.mozGetUserMedia(constraints, success, error);
        } else if (navigator.getUserMedia) {
            //旧版API
            navigator.getUserMedia(constraints, success, error);
        }
    }

    let video = document.getElementById('video');
    video.controls=false;

    function success(stream) {
        //兼容webkit核心浏览器
        let CompatibleURL = window.URL || window.webkitURL;
        //将视频流设置为video元素的源
        console.log(stream);

        //video.src = CompatibleURL.createObjectURL(stream);
        video.srcObject = stream;
        video.play();
    }

    function error(error) {
        console.log(`访问用户媒体设备失败${error.name}, ${error.message}`);
    }

    if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
        //调用用户媒体设备, 访问摄像头
        getUserMedia({video : {width: 250, height: 250}}, success, error);
    } else {
        alert('不支持访问用户媒体');
    }

    var captureImage = function() {
        var canvas = document.createElement("canvas");
        canvas.width = video.videoWidth ;
        canvas.height = video.videoHeight ;
        canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);

        var img = document.createElement("img");
        img.src = canvas.toDataURL("image/png");
        $.ajax({
            type : "POST",
            url : '/face/reco',
            data : {data:img.src},
            timeout : 60000,
            success : function(data){
                console.log("success");
            }
        });
    };
</script>
<script>
    var type = "${sessionScope.user.type}";

    if (type == "1" && "${sessionScope.user.userId}" != "${room.createrId}") {
        type = "0";
    }
    console.log(type);
    <%--var isDark = ${isDark};--%>
    <%--var isCare = ${isCare};--%>
    var state = ${room.teaching};
    <%--var roomIsDark= ${isDarkRoom}--%>


    var fatherWidth = $("#main").width();
    var playBody = (fatherWidth) * 0.7;
    var _width = playBody + ""; //播放器属性
    var _height = playBody / 1.65;
    var _stream = ${room.id};
    if (state) {
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
        if (state == true) {
            createFlash(_width, _height, _stream, _rtmpIp);
        } else {
            createFlash(_width, _height, null, _rtmpIp);
        }
    }

    if (state == true) {
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
</script>

<script>
    function chatMessage(userName,message){
    var liDom = "<li class=\"danmakuUser\"><p><span>"  + userName+ "</span>&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;"+message+"</p> </li>";
    $(".ulDanmaku").append(liDom);
    }

    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + window.location.host + "/WebScoket/" + ${room.id}); //房间号
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
            chatMessage(msg.content.userName, msg.content.content);
            console.log(msg)
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
                userName:"${sessionScope.user.userName}",
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


    function fsubmit() {
        var data = new FormData($('#formData')[0]);
        $.ajax({
            url: '/supermanager/closure',
            type: 'POST',
            data: data,
            dataType: 'JSON',
            cache: false,
            processData: false,
            contentType: false
        }).done(function (ret) {
            $.ajax({
                type: "POST",
                url: "/anchor/change",
                dataType: "json",
                data: "roomId=${room.id}&state=" + 0,
                success: function (data) {
                    layer.open({
                        title: "信息",
                        content: data.message,
                    });
                    // alert(data.message);
                }
            });

        });
        return false;
    }
</script>
</body>
</html>
