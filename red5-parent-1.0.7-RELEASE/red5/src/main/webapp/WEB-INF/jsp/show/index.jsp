<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页livess2ss</title>
    <%@ include file="../common/resources.jsp" %>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        #main {
            width: 90%;
            margin: 0 auto;
        }

        .index-main {
            padding-top: 20px;
        }

        #playBody {
            display: inline-block;
            margin-right: 5px;
        }

        #showNav {
            border-radius: 7px;
            display: inline-block;
            vertical-align: top;
            margin-top: 0px;
            height: 460px;
            background: rgba(121, 121, 121, 1);
        }

        .divs {
            transition: all 0.5s;
            position: relative;
            margin: 0 auto;
            height: 109px;
            margin-top: 5px;
            width: 94%;
        }

        #showNav div img {
            cursor: pointer;
            width: 100%;
            height: 100%;
        }

        #showNav div div {
            display: inline-block;
            position: absolute;
            top: 50%;
            left: -15px;
            /*transform: translate(0, -50%);*/
            transform: translateY(-50%);
        }

        #showNav div div span {
            display: none;
            color: red;
            font-size: 20px;
            z-index: 1000;
        }



        a, a:hover, a:active, a:visited {
            text-decoration: none;
        }

        .footDivBig h3 a,
        .footDivBig h3 a:hover,
        .footDivBig h3 a:active,
        .footDivBig h3 a:visited {
            text-decoration: none;
        }

        #search{
            position: absolute;
            left: 500px;
            top: 200px;
        }

    </style>
</head>
<body style="background-color: #F2F2F2">
<jsp:include page="../common/topNav.jsp"></jsp:include>
<form id="search">
        <div class="form-group">
            <div>
                <center><img src="/static/img/logo/logo-0.png" style="width: 200px; height: 100px"></center>
            </div>
            <input type="text" id="roomId" class="form-control" placeholder="老师的房间号" style="width: 500px;display: inline-block">
            <button type="submit" class="btn btn-primary" id="sub">搜索</button>
        </div>
</form>
<%@ include file="../common/resources-foot.jsp" %>

</body>
<script>
    $(document).on("click", "#sub", function() {
        var roomId = $("#roomId").val();

        $.ajax({
            url: "/liveShow/canSure/"+roomId,
            type: "post",
            data: null,
            dataType: "json",
            success: function(show) {
                if(!show.success)
                {
                    layerMsg(show, nothingDoFun);
                }else {
                    location.href="/liveShow/index/"+roomId
                }
            }
        });

        return false;
    });
</script>
</html>
