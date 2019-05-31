<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>评价查询页</title>
    <%@ include file="../common/resources.jsp" %>
    <link href="/static/plugins/bootstrap/file/css/fileinput.min.css" rel="stylesheet"/>
    <style>
        #main {
            position: absolute;
            top: 51px;
            left: 225px;
            width: 85%;

        }
        .border {border-radius:50px;}

    </style>
</head>
<div id="main">
    <div id="mainBody">
        <form class="form-inline" style="margin-top: 50px;margin-left: 50px">
            <div class="form-group">
                <label for="classId">课程编号</label>
                <input type="text" class="form-control" id="classId" placeholder="课程编号">
            </div>

            <button type="button" class="btn btn-default" id="queryComment">评价查询</button>
        </form>
    </div>
    <div id="danmakuDiv" style="width: 70%;margin-left: 50px;margin-top: 50px;background-color:white">
        <table class="table table-striped" id="danamkuTable">

        </table>
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


    $(document).on("click", "#queryComment", function() {
        var classId = $("#classId").val();
        $.ajax({
            url: "/evaluate/classComment",
            type: "post",
            data: "classId=" + classId,
            dataType: "json",
            success: function(show) {
                if(show.state == 0)
                {
                    layerMsg(show, nothingDoFun);
                }
                else
                {
                    var str = " <table class=\"table table-striped\"><tr><th>发言人</th><th>姓名</th><th>评价内容</th></tr>"
                    if  (show.success && show.data!=null && show.data instanceof  Array) {
                        for  ( var i = 0 ; i <  show.data.length ; i++) {
                            str = str + "<tr><td><div><img class=\"border\" src='"+ show.data[i].imgUrl+"'"+"style=\"width: 30px;height: 30px;\"></div></td><td>"+show.data[i].userName+"</td><td>"+show.data[i].classComment.content+"<td></tr>"
                        }
                    }
                    str = str + "</table>"
                    $("#danmakuDiv").css('background-color',"white")
                    $("#danmakuDiv").html(str);
                }
            }
        });

        return false;
    });

    $('.nav1-item>ul>li').css('background','none')
    $('#navStyle3').css('background','orange')
    $('#navStyle3>a').css('color','#FFF')
    $('#navStyle3>a').css('background','rgba(0,0,0,.1)')
</script>
</html>
