<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>课程管理</title>
    <%@ include file="../common/resources.jsp" %>
    <link href="/static/plugins/bootstrap/file/css/fileinput.min.css" rel="stylesheet"/>
    <style>
        #main {
            position: absolute;
            top: 51px;
            left: 225px;
            width: 85%;

        }

        .class-event{
            display: none;
            margin-top: 30px;
        }

    </style>
</head>
<div id="main">
    <div id="mainBody">
        <div style="margin-top: 30px">
            <a class="btn btn-large btn-primary" type="button" id="btnCreateClass" style="float: right;width: 100px">创建课程</a>
        </div>

        <c:forEach var="cInfo" items="${classInfo}">

            <div class=" col-sm-10" style="margin-top: 50px;">
                <a class="btn btn-large btn-primary btn-block live-show-event" type="button" id="btn${cInfo.id}">${cInfo.className}</a>
                <div class="class-event" id="class${cInfo.id}">
                    <div style="width: 100%;height: 30px">
                        <form id="form${cInfo.id}" enctype="multipart/form-data" style="display: inline-block">
                            <div class="form-group" style="display: inline-block">
                                <label for="file${cInfo.id}">学生名单:txt格式 每行内容:学号</label>
                                <input type="file" id="file${cInfo.id}">
                            </div>
                            <button type="button" class="btn btn-default btn-stu-import" id="btnStuImport${cInfo.id}">导入</button>
                        </form>
                        <div style="width: 100px;float: right;display: inline-block">
                            <a class="btn btn-large btn-primary btn-block userInfo-live-btn" type="button" style="width: 100px;" id="userInfoBtn${cInfo.id}">查看名单</a>
                            <a class="btn btn-large btn-primary btn-block" type="button" style="width: 100px;" href="/quality/teach?classId=${cInfo.id}">查看考勤</a>
                        </div>
                    </div>
                    <div>
                        <div class="form-group" style="margin-top: 20px">
                            <label for="classIdShow${cInfo.id}">课程编号：</label>
                            <input type="text" class="form-control" id="classIdShow${cInfo.id}" placeholder="课程编号" value="${cInfo.id}" disabled/>
                        </div>
                        <div class="form-group" style="margin-top: 20px">
                            <label for="className${cInfo.id}">课程名：</label>
                            <input type="text" class="form-control" id="className${cInfo.id}" placeholder="课程名" value="${cInfo.className}"/>
                        </div>
                        <div class="form-group">
                            <label for="classIntro${cInfo.id}">课程简介：</label>
                            <textarea class="form-control" rows="3" id="classIntro${cInfo.id}" placeholder="课程简介">${cInfo.classIntro}</textarea>
                        </div>
                        <div class="form-group">
                            <center>
                                <button class="btn btn-default btn-warning form-control live-btn-class" rows="3" id="change${cInfo.id}">提交</button>
                            </center>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../common/topNav.jsp"></jsp:include>
<jsp:include page="../common/leftNavTeach.jsp"></jsp:include>
<%@ include file="../common/resources-foot.jsp" %>
</body>
<script src="/static/js/plugins/echarts.min.js"/>
<script src="/static/js/plugins/jquery-3.2.1.min.js"></script>
<script src="/static/js/user/common.js"></script>
<script src="/static/plugins/bootstrap/file/js/fileinput.min.js"></script>
<script src="/static/plugins/bootstrap/file/js/locales/zh.js"></script>
<script>
$(".live-show-event").click(function(){
    var id = $(this).attr("id");
    var cid = id.substring(3,id.length);
    if ( $("#class"+cid).css("display") == 'none' ) {
        $("#class"+cid).css("display",'block');
    } else{
        $("#class"+cid).css("display",'none');
    }
})

$(document).on("click", ".live-btn-class", function() {
    var id = $(this).attr("id");
    var idNum = id.substring(6,id.length);
    var className = $("#className"+idNum).val();
    var classInfo = $("#classIntro"+idNum).val();


        $.ajax({
            url: "/classInfo/updateInfo",
            type: "post",
            data: "classId=" + idNum + "&className=" + className+"&classInfo="+classInfo,
            dataType: "json",
            success: function(show) {
                if (show.success) {
                    window.location.href = "/classInfo/index"
                }else {
                    window.location.href="/"
                }
            }
        });

});
$(document).on("click", "#btnCreateClass", function() {
    layer.open({
        title: "",
        content: $("#createClass").html(),
        area: ['500px', '500px'],
        btn: [],
    })
});

$(document).on("click", "#createClassInfo", function() {
    var className = $("#className").val();
    var classIntro= $("#classIntro").val();

    $.ajax({
        url: "/classInfo/create",
        type: "post",
        data: "className=" + className + "&classInfo=" + classIntro,
        dataType: "json",
        success: function(show) {
            if(show.status == 0)
            {
                layerMsg(show, nothingDoFun);
            }
            else
            {
                layerMsg(show, flush);
            }
        }
    });

    return false;
});


$(document).on("click", ".btn-stu-import", function(){
    var btnId = $(this).attr("id");
    var classId = btnId.substring(12, btnId.length);
    var uploadFile = new FormData();
    uploadFile.append("file",$("#form"+classId)[0][0].files[0])
    uploadFile.append("classId", classId);
    if("undefined" != typeof(uploadFile) && uploadFile.get("file")!=null && uploadFile.get("classId")!=null){
        $.ajax({
            url:'/classInfo/uploadUCmapping',
            type:'POST',
            data:uploadFile,
            async: false,
            cache: false,
            contentType: false, //不设置内容类型
            processData: false, //不处理数据
            success:function(data){
                layer.msg(data.message, {
                    icon: 1,
                    time: 2000
                }, function()
                {

                });
            },
            error:function(){
                layer.msg("上传失败", {
                    icon: 1,
                    time: 2000
                }, function()
                {

                });            }
        })
    }else {
        alert("选择的文件无效！请重新选择");
    }
})
$(document).on("click", ".userInfo-live-btn", function() {
    var id = $(this).attr("id");
    var classId = id.substring(11, id.length);

    $.ajax({
        url: "/classInfo/usersInfo",
        type: "post",
        data: "classId=" + classId,
        dataType: "json",
        success: function(msg) {
            if(msg.state == 0)
            {
                layerMsg(msg, nothingDoFun);
            }
            else
            {
                console.log(msg)
                if (msg.data != null && msg.data instanceof Array) {
                    var str = "<center><h4>学生名单</h4></center>";
                    str += " <table class=\"table table-striped\"><tr><th>头像</th><th>学号</th><th>姓名</th></tr>"

                    for (var i = 0 ; i < msg.data.length; i ++ ) {
                        str = str.concat("<tr><td><div><img class=\"border\" src='"+ msg.data[i].imgAddress+"'"+"style=\"width: 30px;height: 30px;\"></div></td><td>"+msg.data[i].userId+"</td><td>"+msg.data[i].userName+"<td></tr>")
                    }
                    $("#stuList").html(str)

                    layer.open({
                        title: "",
                        content: $("#stuList").html(),
                        area: ['500px', '500px'],
                        btn: [],
                    })
                }
            }
        }
    });

    return false;
});
$('.nav1-item>ul>li').css('background','none')
$('#navStyle2').css('background','orange')
$('#navStyle2>a').css('color','#FFF')
$('#navStyle2>a').css('background','rgba(0,0,0,.1)')
</script>
</html>
