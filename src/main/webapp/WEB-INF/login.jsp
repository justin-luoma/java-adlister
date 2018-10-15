<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            Login
        </div>
        <div class="card-body">
            <form action="/login" method="post" id="login-form">
                <fieldset>
                    <div class="form-group">
                        <label for="username">Username or Email</label>
                        <input type="text" class="form-control" name="username" id="username" placeholder="Enter username or email">
                        <%--<small id="usernameHelp" class="form-text text-muted">Create a unique username.</small>--%>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                        <%--<small id="passwordHelp" class="form-text text-warning"></small>--%>
                    </div>
                    <c:if test="${sessionScope.error}">
                        <div class="form-group m-0">
                            <label class="form-text text-warning">
                                Invalid username/email or password!
                            </label>
                        </div>
                    </c:if>
                </fieldset>
            </form>
        </div>
        <div class="card-footer">
            <button type="submit" id="submit" class="btn btn-primary">Login</button>
        </div>
    </div>



</div>

<script src="/static/login.js"></script>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
