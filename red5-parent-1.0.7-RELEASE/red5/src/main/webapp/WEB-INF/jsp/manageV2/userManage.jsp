<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息维护</title>
    <%@ include file="../common/resources.jsp" %>
    <link href="/static/plugins/bootstrap/file/css/fileinput.min.css" rel="stylesheet"/>
    <style>
        #main{
            position: absolute;
            top: 51px;
            left: 225px;
            width: 85%;

        }
        .border {border-radius:10px;}
    </style>
</head>
<div id="main">

    <center><form class="form-horizontal border" style="margin-top: 50px;background-color: white;width: 90%;height: 400px;margin-bottom: 50px">
        <h4 style="padding-top: 40px;font-family: Microsoft YaHei">修改密码</h4>
        <div class="form-group" style="padding-top: 5%">
            <label for="userId" class="col-sm-3 control-label">学号：</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" id="userId" value="${sessionScope.user.userId}" disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="rpassword" class="col-sm-3 control-label">新密码：</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="rPassword" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <label for="surePassword" class="col-sm-3 control-label">确认密码：</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="surePassword" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-6">
                <a class="btn btn-large btn-primary btn-block" type="button" id="changePsd">修改密码</a>
            </div>
        </div>
    </form></center>
    <center><div style="width: 90%; background-color: white;" class="border">
        <center><h4 style="padding-top: 40px;font-family: Microsoft YaHei">上传图片</h4></center>
        <input type="file" id="testfile" name="file" multiple style="width: 80%" />
    </div></center>
    <div style="margin-top: 50px"></div>
</div>
<jsp:include page="../common/topNav.jsp"></jsp:include>

<c:if test="${sessionScope.user.type==0}">
    <jsp:include page="../common/leftNav.jsp"></jsp:include>

</c:if>
<c:if test="${sessionScope.user.type==1}" var="flag" scope="session">
    <jsp:include page="../common/leftNavTeach.jsp"></jsp:include>
</c:if>
<%@ include file="../common/resources-foot.jsp" %>
</body>
<script src="/static/js/plugins/jquery-3.2.1.min.js"></script>
<script src="/static/js/user/common.js"></script>
<script src="/static/plugins/bootstrap/file/js/fileinput.min.js"></script>
<script src="/static/plugins/bootstrap/file/js/locales/zh.js"></script>
<script>
    $('#testfile').fileinput({
        language: 'zh',
        uploadUrl:'/userInfo/img',
        showCaption: true,
        showUpload: true,
        showRemove: true,
        showClose: false,
        autoReplace:true,
        minFileCount:1,
        showCancel:false,
        allowedFileTypes: ['image'],
        allowedFileExtensions:  ['jpg', 'png'],
        maxFileCount:1,
        layoutTemplates:{
            actionDelete: ''
        },
        browseClass: 'btn btn-primary'
    }).on("fileuploaded", function (event, data, previewId, index){
           layerMsg(data.response,null);
    });


    $(document).on("click", "#changePsd", function() {
        var loginName = $("#userId").val();
        var rPassword = $("#rPassword").val();
        var surePassword = $("#surePassword").val();

        if  (rPassword == surePassword ) {
            $.ajax({
                url: "/userInfo/changePassword",
                type: "post",
                data: "password=" + rPassword + "&rPassword=" + surePassword,
                dataType: "json",
                success: function(show) {
                    if(show.status == 0)
                    {
                        layerMsg(show, null);
                    }
                    else
                    {
                        layerMsg(show, function () {
                            window.location.href="/";
                        });

                    }
                }
            });
        } else {
            layer.msg("密码不一致", {
                icon: 1,
                time: 2000
            }, function()
            {

            });
        }

        return false;
    });
    $('.nav1-item>ul>li').css('background','none')
    $('#navStyle0').css('background','orange')
    $('#navStyle0>a').css('color','#FFF')
    $('#navStyle0>a').css('background','rgba(0,0,0,.1)')
</script>
</html>
