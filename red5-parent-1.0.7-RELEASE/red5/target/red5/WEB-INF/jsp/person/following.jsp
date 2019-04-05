<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" reason="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" reason="ie=edge">
    <title>我的关注</title>
    <%@ include file="../common/resources.jsp"%>
    <link rel="stylesheet" href="/static/css/user/user.css" />
</head>

<body>
<jsp:include page="../common/topNav.jsp"></jsp:include>
    <div class="container live-container">
        <div class="row">
            <div class="col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10">
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="page-header">
                            <div>个人中心</div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-2 col-md-2">
                        <div class="user-side">
                            <ul class="user-side-ul">
                                <li>
                                    <a href="/person/">
                                        <i class="fa fa-vcard fa-fw"></i> &nbsp;个人资料
                                    </a>
                                </li>
                                <li>
                                    <a href="/person/following" class="clicked">
                                        <i class="fa fa-user-o fa-fw"></i> &nbsp;我的关注
                                    </a>
                                </li>
                                <c:choose>
                                    <c:when test="${sessionScope.user.type == 2}">
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-tv fa-fw"></i> &nbsp;超管相关
                                                <i class="live-caret"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="/person/managerApplication" class="li-children">
                                                <i class="fa fa-fw"></i> &nbsp;申请管理
                                            </a>
                                        </li>
                                        <li>
                                            <a href="/person/managerRoomMute" class="li-children">
                                                <i class="fa fa-fw"></i> &nbsp;主播封禁
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-tv fa-fw"></i> &nbsp;直播相关
                                                <i class="live-caret"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="/person/application" class="li-children">
                                                <i class="fa fa-fw"></i> &nbsp;直播申请
                                            </a>
                                        </li>
                                        <li>
                                            <a href="/person/liveSetting" class="li-children">
                                                <i class="fa fa-fw"></i> &nbsp;直播设置
                                            </a>
                                        </li>
                                        <li>
                                            <a href="/person/liveRecord" class="li-children">
                                                <i class="fa fa-fw"></i> &nbsp;直播记录
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-10 col-md-10">
                        <div class="user-body">
                            <ol class="breadcrumb">
                                <i class="fa fa-vcard"></i> &nbsp;
                                <li class="active">我的关注</li>
                            </ol>
                            <div class="user-following-main">
                                <c:forEach items="${personalFollowingDTOList}" var="following">
                                    <div class="user-following">
                                        <div class="user-following-container">
                                            <div class="user-following-left">
                                                <img src="${following.livePhoto}" alt="">
                                            </div>
                                            <div class="user-following-right">
                                                <div class="user-following-title">
                                                    <a href="/liveShow/index/${following.roomId}" target="_blank" title="${following.roomName}">${following.roomName}</a>
                                                </div>
                                                <div class="user-following-name">
                                                    ${following.nickName}
                                                </div>
                                                <c:choose>
                                                    <c:when test="${following.liveState}">
                                                        <div class="user-following-state">
                                                            <i class="fa fa-clock-o"></i>
                                                            正在直播&nbsp;&nbsp;
                                                            <i class="fa fa-fire"></i>
                                                                ${following.attendance}
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="user-following-state">
                                                            <i class="fa fa-clock-o"></i>
                                                            未直播&nbsp;&nbsp;
                                                            <i class="fa fa-fire"></i>
                                                                0
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                                <div class="user-following-type">
                                                    <a href="/directory/module/${following.moduleId}">${following.moduleName}</a>
                                                    <a href="javascript:;" class="user-following-unfollowing" room-id="${following.roomId}">取消关注</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <%--<div class="user-following">--%>
                                    <%--<div class="user-following-container">--%>
                                        <%--<div class="user-following-left">--%>
                                            <%--<img src="/static/img/228989_1942.jpg" alt="">--%>
                                        <%--</div>--%>
                                        <%--<div class="user-following-right">--%>
                                            <%--<div class="user-following-title">--%>
                                                <%--<a href="#" target="_blank" title="田泽儿：周二喜相逢~相逢~逢~">田泽儿：周二喜相逢~相逢~逢~</a>--%>
                                            <%--</div>--%>
                                            <%--<div class="user-following-name">--%>
                                                <%--dfsahjkd--%>
                                            <%--</div>--%>
                                            <%--<div class="user-following-state">--%>
                                                <%--<i class="fa fa-clock-o"></i>--%>
                                                <%--已播88分钟&nbsp;&nbsp;--%>
                                                <%--<i class="fa fa-fire"></i>--%>
                                                <%--970278--%>
                                            <%--</div>--%>
                                            <%--<div class="user-following-type">--%>
                                                <%--<a href="#">绝地求生</a>--%>
                                                <%--<a href="#" class="user-following-unfollowing">取消关注</a>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<%@ include file="../common/resources-foot.jsp" %>
<script>
    function change() {
        var ratio = 395 / 135; // 宽 / 长
        var $userFollowing = $(".user-following");
        var userFollowingWidth = $userFollowing.width();
        var userFollowingHeight = userFollowingWidth / ratio;
        $userFollowing.height(userFollowingHeight + "px");

        var $img = $(".user-following-main .user-following-container img");

        $(".user-following-container .user-following-left").height(userFollowingHeight - 10)
        $img.height(userFollowingHeight - 20);
        var imgWidth = $img.width();

        var userFollowingRightWidth = userFollowingWidth - 10 - imgWidth;
        $(".user-following-right").width(userFollowingRightWidth - 5);
    }

    change();

    $(window).resize(
        function () {
            change();
        }
    );

    //取消关注
    $(".user-following-unfollowing").click(function()
    {
        var roomId = $(this).attr("room-id");
        console.log(roomId);
        $.ajax({
            url: "unfollowing",
            type: "post",
//            contentType: "application/json",
//            data: JSON.stringify(),
            data: "roomId=" + roomId,
            dataType: "json",
            success: function(show)
            {
                console.log(show);
                console.log(show.state == 1);
                if(show.state == 1)
                {
                    layer.msg(show.message, {
                        icon: 1,
                        time: 2000
                    }, function(){
                        location = location;
                    });
                }
                else
                {
                    layer.msg(show.message, {
                        icon: 2,
                        time: 2000
                    }, function(){
                        location = location;
                    });
                }
            }
        })
    })
</script>

</html>
