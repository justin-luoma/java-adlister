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
    <c:forEach var="ad" items="${sessionScope.pagedResults}">

            <div class="card mt-1">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${ad.title()}" /></h5>
                    <%--<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>--%>
                    <p class="card-text"><c:out value="${ad.description()}" /></p>
                </div>
                <div class="card-footer d-flex justify-content-between p-1">
                    <span class="align-text-bottom">categories: </span>
                    <button type="button" class="btn btn-success btn-sm" onclick="window.location='<c:out value="/ad/${ad.id()}" />'">View</button>
                </div>
            </div>
    </c:forEach>

    <div class="col d-flex justify-content-center my-3">
        <div class="btn-toolbar align-items-center" role="toolbar" aria-label="Pages toolbar">

            <c:if test="${sessionScope.first > 1}" >
                <div class="btn-group mr-5" role="group">
                    <button type="button" class="btn btn-secondary" onclick="goToPage(1)">First</button>
                </div>
            </c:if>

            <c:if test="${sessionScope.page != 1}" >
                <div class="btn-group mr-5" role="group">
                    <span class="fas fa-chevron-left hoverCursor" onclick="goToPage(${sessionScope.page - 1})"></span>
                </div>
            </c:if>

            <div class="btn-group mx-2" role="group">

                <c:forEach  var="page" begin="${sessionScope.first}" end="${sessionScope.last}">
                    <c:choose>
                        <c:when test="${sessionScope.page == page}">
                            <button type="button" class="btn btn-link disabled" onclick="goToPage(${page})">${page}</button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-link" onclick="goToPage(${page})">${page}</button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </div>

            <c:if test="${sessionScope.page != sessionScope.pages}" >
                <div class="btn-group ml-5" role="group">
                    <span class="fas fa-chevron-right hoverCursor" onclick="goToPage(${sessionScope.page + 1})"></span>
                </div>
            </c:if>

            <c:if test="${sessionScope.last < sessionScope.pages}" >
                <div class="btn-group ml-5" role="group">
                    <button type="button" class="btn btn-secondary" onclick="goToPage(${sessionScope.pages})">Last</button>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="/static/ads.js"></script>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
