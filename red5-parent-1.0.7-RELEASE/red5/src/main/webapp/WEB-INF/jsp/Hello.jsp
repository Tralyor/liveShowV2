<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<script type="text/javascript" src="/play/js/swfobject.js"></script>
<div class="video" id="CuPlayer"> <b> <img src="/play/images/loading.gif" /> 网页视频播放器加载中，请稍后...</b> </div>
<script type="text/javascript">
    var so = new SWFObject("/play/player.swf","ply","980","460","9","#000000");
    so.addParam("allowfullscreen","true");
    so.addParam("allowscriptaccess","always");
    so.addParam("wmode","opaque");
    so.addParam("quality","high");
    so.addParam("salign","lt");
    <!-- HTML代码参数/Begin -->
    so.addVariable("JcScpServer","rtmp://127.0.0.1/oflaDemo"); //您的rtmp流媒体服务器地址 
    so.addVariable("JcScpVideoPath","cat"); //流名称
    so.addVariable("JcScpImg","oflaDemoTest/play/player/images/startpic.jpg"); //视频缩略图
    so.addVariable("JcScpFile","player/CuSunV4set.xml"); //配置文件
    <!-- HTML代码参数/End -->
    so.write("CuPlayer");
</script>
</body>
</html>
</body>
</html>