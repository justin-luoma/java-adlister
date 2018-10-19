<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/assests/head.jsp">
        <jsp:param name="title" value="Adlister" />
    </jsp:include>
</head>
<body>

<%@ include file="navbar/navbar.jsp" %>

<div class="container" id="content">
    <div class="jumbotron">
        <h1 class="display-3">Welcome to Adlister!</h1>
        <%--<p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>--%>
        <%--<hr class="my-4">--%>
        <%--<p>It uses utility classes for typography and spacing to space content out within the larger container.</p>--%>
        <div class="row mt-5">
            <div class="col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center mb-3">
                <div class="card imgW hoverCursor" onclick="window.location='/ads?category=3'" >
                    <img class="card-img-top" height="181px" width="360px" src="/static/img/appliances_360-181.png" alt="appliances">
                    <div class="card-body">
                        <h5 class="card-title">appliances</h5>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center mb-3">
                <div class="card imgW hoverCursor" onclick="window.location='/ads?category=10'" >
                    <img class="card-img-top" height="181px" width="360px" src="/static/img/books_360-181.png" alt="appliances">
                    <div class="card-body">
                        <h5 class="card-title">books</h5>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center mb-3">
                <div class="card imgW hoverCursor" onclick="window.location='/ads?category=5'" >
                    <img class="card-img-top" height="181px" width="360px" src="/static/img/cellPhones_360-181.png" alt="appliances">
                    <div class="card-body">
                        <h5 class="card-title">cell phones</h5>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center mb-3">
                <div class="card imgW hoverCursor" onclick="window.location='/ads?category=6'" >
                    <img class="card-img-top" height="181px" width="360px" src="/static/img/clothes_360-181.png" alt="appliances">
                    <div class="card-body">
                        <h5 class="card-title">clothes</h5>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center mb-3">
                <div class="card imgW hoverCursor" onclick="window.location='/ads?category=8'" >
                    <img class="card-img-top" height="181px" width="360px" src="/static/img/computers_360-181.png" alt="appliances">
                    <div class="card-body">
                        <h5 class="card-title">computers</h5>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center mb-3">
                <div class="card imgW hoverCursor" onclick="window.location='/ads?category=1'" >
                    <img class="card-img-top" height="181px" width="360px" src="/static/img/furniture_360-181.png" alt="appliances">
                    <div class="card-body">
                        <h5 class="card-title">furniture</h5>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-4 d-flex justify-content-center mb-3">
                <div class="card imgW hoverCursor" onclick="window.location='/ads?category=2'" >
                    <img class="card-img-top" height="181px" width="360px" src="/static/img/videoGames_360-181.png" alt="appliances">
                    <div class="card-body">
                        <h5 class="card-title">videos games</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>