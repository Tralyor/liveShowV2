<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/5
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/login" method="post">
    <input type="text" name="loginName">
    <input type="password" name="password">
    <input type="submit">
</form>
</body>
</html>
