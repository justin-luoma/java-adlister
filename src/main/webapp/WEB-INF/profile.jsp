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

<div class="container col-md-8" id="content">
    <div class="card bg-light mb-3">
        <div class="card-header"><h1>Welcome, <c:out value="${sessionScope.user.username()}"/></h1></div>
        <div class="card-body">
            <form>
                <fieldset>
                    <legend>Your Details</legend>
                    <fieldset>
                        <%--viewing--%>
                        <div class="form-group row viewing username">
                            <label for="staticUsername" class="col-12 col-form-label">Username</label>
                            <div class="col-10">
                                <input type="text" readonly class="form-control-plaintext" id="staticUsername" value='<c:out value="${sessionScope.user.username()}"/>'>
                            </div>
                            <div class="col-sm-2 d-flex justify-content-end">
                                <input type="button" class="btn btn-sm editBtn" data-target="username" value="Edit">
                            </div>
                        </div>
                        <%--editing--%>
                        <div class="form-group row editing d-none username">
                            <label class="col-12 col-form-label" for="username">New Username</label>
                            <div class="col-12 mb-2">
                                <input type="text" value='<c:out value="${sessionScope.user.username()}"/>' class="form-control" id="username" aria-describedby="usernameInfo">
                                <small id="usernameInfo" class="form-text"></small>
                            </div>
                            <div class="col-sm-12 d-flex justify-content-end">
                                <input type="button" class="btn btn-primary btn-sm updateBtn mr-1" data-target="username" value="Update">
                                <input type="button" class="btn btn-sm cancelBtn" data-target="username" value="Cancel">
                            </div>
                        </div>
                    <hr>
                        <%--viewing--%>
                        <div class="form-group row viewing email">
                            <label for="staticEmail" class="col-12 col-form-label">Email</label>
                            <div class="col-10">
                                <input type="text" readonly class="form-control-plaintext" id="staticEmail" value='<c:out value="${sessionScope.user.email()}"/>'>
                            </div>
                            <div class="col-sm-2 d-flex justify-content-end">
                                <input type="button" class="btn btn-sm editBtn" data-target="email" value="Edit">
                            </div>
                        </div>
                        <%--editing--%>
                        <div class="form-group row editing d-none email">
                            <label class="col-12 col-form-label" for="email">New Email</label>
                            <div class="col-12 mb-2">
                                <input type="text" value='<c:out value="${sessionScope.user.email()}"/>' class="form-control" id="email" aria-describedby="emailInfo">
                                <small id="emailInfo" class="form-text"></small>
                            </div>
                            <div class="col-sm-12 d-flex justify-content-end">
                                <input type="button" class="btn btn-primary btn-sm updateBtn mr-1" data-target="email" value="Update">
                                <input type="button" class="btn btn-sm cancelBtn" data-target="email" value="Cancel">
                            </div>
                        </div>
                    <hr>
                    </fieldset>
                    <fieldset>
                        <%--viewing--%>
                        <div class="form-group row viewing password mb-0">
                            <div class="col-sm-3 d-flex">
                                <input type="button" class="btn btn editBtn" data-target="password" value="Change password">
                            </div>
                        </div>
                            <%--editing--%>
                            <div class="form-group row editing d-none password mb-0">
                                <label class="col-12 col-form-label" for="passwordCurrent">Current Password</label>
                                <div class="col-12">
                                    <input type="password" class="form-control" id="passwordCurrent" aria-describedby="passwordCurrentInfo">
                                    <small id="passwordCurrentInfo" class="form-text"></small>
                                </div>
                                <label class="col-12 col-form-label" for="password">New Password</label>
                                <div class="col-12">
                                    <input type="password" class="form-control" id="password" aria-describedby="passwordInfo">
                                    <small id="passwordInfo" class="form-text"></small>
                                </div>
                                <label class="col-12 col-form-label" for="passwordVerify">Verify New Password</label>
                                <div class="col-12 mb-2">
                                    <input type="password" class="form-control" id="passwordVerify" aria-describedby="passwordVerifyInfo">
                                    <small id="passwordVerifyInfo" class="form-text"></small>
                                </div>
                                <div class="col-sm-12 d-flex justify-content-end">
                                    <input type="button" class="btn btn-primary btn-sm updateBtn mr-1" data-target="password" value="Update">
                                    <input type="button" class="btn btn cancelBtn" data-target="password" value="Cancel">
                                </div>
                            </div>
                    </fieldset>
                </fieldset>
            </form>
        </div>
    </div>


</div>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

<script src="/static/profile.js" ></script>

</body>
</html>
