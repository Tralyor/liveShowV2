<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>课程评价</title>
    <%@ include file="../common/resources.jsp" %>
    <link href="/static/plugins/bootstrap/file/css/fileinput.min.css" rel="stylesheet"/>
    <style>
        #main {
            position: absolute;
            top: 51px;
            left: 225px;
            width: 85%;

        }
        .live-event-chart{
            display: none;
            transition: all .2s linear;
        }
    </style>
</head>
<div id="main">
    <div id="mainBody">
        <c:forEach var="uClass" items="${userClass}">

            <div class=" col-sm-10" style="margin-top: 50px;">
                <a class="btn btn-large btn-primary btn-block live-event" type="button" id="btn${uClass.tclass.id}">${uClass.tclass.className}</a>
                <div>
                    <center>
                        <div id="btn${uClass.tclass.id}Info" class="live-event-chart" style="height: 200px">
                            <div>
                                <div class="form-group">
                                    <label for="classIntro${uClass.classComment.id}">课程评价</label>
                                    <textarea class="form-control" rows="3" id="comment${uClass.tclass.id}" placeholder="课程评价">${uClass.classComment.content}</textarea>
                                </div>
                                <div class="form-group">
                                    <center>
                                        <button class="btn btn-default btn-warning form-control live-btn-class" rows="3" id="change${uClass.tclass.id}">提交</button>
                                    </center>
                                </div>
                            </div>
                        </div>
                    </center>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../common/topNav.jsp"></jsp:include>
<jsp:include page="../common/leftNav.jsp"></jsp:include>
<%@ include file="../common/resources-foot.jsp" %>
</body>
<script src="/static/js/plugins/echarts.min.js"/>
<script src="/static/js/plugins/jquery-3.2.1.min.js"></script>
<script src="/static/js/user/common.js"></script>
<script src="/static/plugins/bootstrap/file/js/fileinput.min.js"></script>
<script src="/static/plugins/bootstrap/file/js/locales/zh.js"></script>
<script>
    $(".live-event").click(function(){
        var id = $(this).attr("id");
        var btnId = "#"+id+"Info";
        var btnTitle= "#"+id +"Title"
        if  ($(btnId).css('display')!="none") {
            $(btnId).css('display','none')
            $(btnTitle).css('display','none')
        } else {
            $(btnId).css('display','block')
            $(btnTitle).css('display','block')
        }
    })

    $(document).on("click", ".live-btn-class", function() {
        var id = $(this).attr("id");
        var idNum = id.substring(6,id.length);
        var contentId = "#"+"comment"+idNum;
        var content = $(contentId).val();
        $.ajax({
            url: "/evaluate/addEvaluate",
            type: "post",
            data: "classId=" + idNum + "&content=" + content,
            dataType: "json",
            success: function(show) {
                layerMsg(show,nothingDoFun())
            }
        });

    })

    $('.nav1-item>ul>li').css('background','none')
    $('#navStyle2').css('background','orange')
    $('#navStyle2>a').css('color','#FFF')
    $('#navStyle2>a').css('background','rgba(0,0,0,.1)')
</script>
</html>
