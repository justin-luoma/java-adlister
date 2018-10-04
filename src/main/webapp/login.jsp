<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 10/4/18
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.username == 'admin' && param.password == 'password'}">
    <c:redirect url="profile.jsp" />
</c:if>

<html>
<head>
    <title>Login</title>
</head>
<body>
    <%@ include file="partials/header.jsp" %>

    <form action="login.jsp" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username">
        <br>
        <label for="password">Password:</label>
        <input type="text" name="password" id="password">
        <br>
        <input type="submit" value="Submit">
    </form>

</body>
</html>
