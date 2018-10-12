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



        <div class="btn-toolbar row d-flex justify-content-center align-items-center my-3" role="toolbar" aria-label="Pages toolbar">
            <%--First page button--%>
            <c:choose>
                <c:when test="${sessionScope.first > 1}">
                    <div class="col-2 col-md-1 text-right">
                        <div class="btn-group" role="group">
                            <span class="hoverCursor" onclick="goToPage(1)"><i class="fas fa-chevron-left"></i><i class="fas fa-chevron-left"></i></span>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-2 col-md-1">
                    </div>
                </c:otherwise>
            </c:choose>

            <%--Previous button--%>
                <c:choose>
                    <c:when test="${sessionScope.page != 1}">
                        <div class="col-1">
                            <div class="btn-group" role="group">
                                <span class="fas fa-chevron-left hoverCursor" onclick="goToPage(${sessionScope.page - 1})"></span>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-1">
                        </div>
                    </c:otherwise>
                </c:choose>

            <%--Pages button group--%>
                <div class="col-6 col-md-3 d-flex justify-content-center">
                <div class="btn-group" role="group">
                    <c:forEach  var="page" begin="${sessionScope.first}" end="${sessionScope.last}">
                        <c:choose>
                            <c:when test="${sessionScope.page == page}">
                                <button type="button" class="btn btn-link px-1 disabled" onclick="goToPage(${page})">${page}</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-link px-1" onclick="goToPage(${page})">${page}</button>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                </div>

            <%--Next page button--%>
                <c:choose>
                    <c:when test="${sessionScope.page != sessionScope.pages}">
                        <div class="col-1 text-right">
                            <div class="btn-group" role="group">
                                <span class="fas fa-chevron-right hoverCursor" onclick="goToPage(${sessionScope.page + 1})"></span>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-1">
                        </div>
                    </c:otherwise>
                </c:choose>

            <%--Last page button--%>
                <c:choose>
                    <c:when test="${sessionScope.last < sessionScope.pages}">
                        <div class="col-2 col-md-1">
                            <div class="btn-group" role="group">
                                <span class="hoverCursor" onclick="goToPage(${sessionScope.pages})"><i class="fas fa-chevron-right"></i><i class="fas fa-chevron-right"></i></span>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-2 col-md-1">
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>


</div>

<script src="/static/ads.js"></script>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
