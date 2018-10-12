<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 10/8/18
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/assests/head.jsp">
        <jsp:param name="title" value="Adlister" />
    </jsp:include>
</head>
<body>
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>


<div class="container" id="content">

    <div class="card">
        <div class="card-header">
            Register
        </div>
        <div class="card-body">
            <form action="/register" method="post" id="register-form">
                <fieldset>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" name="username" id="username" aria-describedby="usernameHelp" placeholder="Enter username">
                        <small id="usernameHelp" class="form-text text-muted">Create a unique username.</small>
                    </div>
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" id="password" aria-describedby="passwordHelp" placeholder="Password"
                               data-toggle="tooltip" data-placement="right" data-html="true"
                               title="1 upper<br />1 lower<br />1 number<br />1 special<br />Min 8 characters">
                        <small id="passwordHelp" class="form-text text-warning"></small>
                    </div>
                    <div class="form-group">
                        <label for="passwordVerify">Verify Password</label>
                        <input type="password" class="form-control" name="passwordVerify" id="passwordVerify" aria-describedby="passwordVerifyHelp" placeholder="Verify Password">
                        <small id="passwordVerifyHelp" class="form-text text-warning"></small>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="card-footer">
            <button type="submit" id="submit" class="btn btn-primary disabled">Submit</button>
        </div>
    </div>



</div>

<c:if test="${sessionScope.data_user != null}">
    <script>
        let data_user = ${sessionScope.data_user};
    </script>
</c:if>

<c:if test="${sessionScope.data_errors != null}">
    <script>
        let data_errors = ${sessionScope.data_errors};
    </script>
</c:if>

<script src="/static/register.js"></script>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
