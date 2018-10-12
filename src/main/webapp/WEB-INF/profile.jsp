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
    <h1>Welcome, <c:out value="${sessionScope.user.username()}"/></h1>
    <c:forEach var="ad" items="${ads}">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${ad.title()}" /></h5>
                    <h6 class="card-subtitle mb-2 text-muted"><c:out value="${ad.created()}" /></h6>
                    <p class="card-text"><c:out value="${ad.description()}" /></p>
                </div>
                <div class="card-footer d-flex justify-content-between p-1">
                    <span class="align-text-bottom">categories: </span>
                    <button type="button" class="btn btn-success btn-sm">View</button>
                </div>
            </div>
    </c:forEach>
</div>


<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
