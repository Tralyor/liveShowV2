<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/4
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/header.jsp"%>
<nav class="navbar navbar-inverse navbar-fixed-top live-shadow" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="navbar-header">
                    <div style="line-height: 50px;">
                        <img style="height: 50px;" src="/static/img/logo/logo-0.png" alt="">
                    </div>
                </div>

                <!-- 左边部分 -->
                <ul class="nav navbar-nav live-left">
                    <li class="">
                        <a href="/">首页</a>
                    </li>
                </ul>

                <!-- 右边部分 -->
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="javascript:;" id="loginA">
                                    <span class="glyphicon glyphicon-log-in"></span> 登录
                                </a>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <div class="nav navbar-nav navbar-right">
                            <div class="live-user">
                                <div class="live-user-img">
                                    <img src="/static/img/logo.jpg" alt="">
                                    <div class="live-nav">
                                        <div class="live-shadow live-border live-border-radius">
                                            <div class="anim">
                                                <div class="user-main">
                                                    <div class="user-info">
                                                        <a href="#">欢迎，${user.userName}</a>
                                                        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                        <a href="/user/loginOut">退出</a>
                                                    </div>
                                                    <div class="user-pic">
                                                        <img src="/static/img/logo.jpg" alt="">
                                                    </div>
                                                    <div class="user-btns">
                                                        <a class="btn btn-large btn-primary btn-block" href="/userInfo/self">个人中心</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</nav>
