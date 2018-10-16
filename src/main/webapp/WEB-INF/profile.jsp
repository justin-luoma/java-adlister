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
    <div class="card bg-light mb-3">
        <div class="card-header"><h1>Welcome, <c:out value="${sessionScope.user.username()}"/></h1></div>
        <div class="card-body">
            <%--<h4 class="card-title"></h4>--%>
            <%--<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>--%>
            <form>
                <fieldset>
                    <legend>Your Details</legend>
                    <fieldset>
                    <div class="form-group row">
                        <label for="staticUsername" class="col-12 col-form-label">Username</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control-plaintext" id="staticUsername" value='<c:out value="${sessionScope.user.username()}"/>'>
                        </div>
                        <div class="col-sm-2 d-flex justify-content-end">
                            <input type="button" class="btn btn-sm" value="Edit">
                        </div>
                    </div>
                    <hr>
                    <div class="form-group row">
                        <label for="staticEmail" class="col-12 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control-plaintext" id="staticEmail" value='<c:out value="${sessionScope.user.email()}"/>'>
                        </div>
                        <div class="col-sm-2 d-flex justify-content-end">
                            <input type="button" class="btn btn-sm" value="Edit">
                        </div>
                    </div>
                    <hr>
                    </fieldset>
                    <fieldset>
                    <div class="form-group row mb-0">
                        <%--<label for="staticEmail" class="col-12 col-form-label">Email</label>--%>
                        <%--<div class="col-sm-10">--%>
                            <%--<input type="text" readonly class="form-control-plaintext" id="staticEmail" value='<c:out value="${sessionScope.user.email()}"/>'>--%>
                        <%--</div>--%>
                        <div class="col-sm-3 d-flex">
                            <input type="button" class="btn btn" value="Change password">
                        </div>
                    </div>
                    </fieldset>
                    <%--<div class="form-group">--%>
                        <%--<label for="exampleInputEmail1">Email address</label>--%>
                        <%--<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">--%>
                        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label for="exampleInputPassword1">Password</label>--%>
                        <%--<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">--%>
                    <%--</div>--%>
                </fieldset>
            </form>
        </div>
    </div>


</div>


<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
