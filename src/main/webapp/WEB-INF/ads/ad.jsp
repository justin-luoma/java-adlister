<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="../assests/head.jsp">
        <jsp:param name="title" value="Adlister" />
    </jsp:include>
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<div class="container" id="content">
            <div class="card">
                <div class="card-header">
                    <div class="row d-flex justify-content-between">
                        <div class="col-12 col-md-8">
                            <h2><c:out value="${ad.title()}" /></h2>
                        </div>
                        <div class="col-12 col-md-4 text-right">
                            <span>Created by: <c:out value="${user_info}" default="test_user" /></span>
                        </div>
                    </div>

                </div>
                <div class="card-body">
                    <%--<h5 class="card-title">${ad.title()}</h5>--%>
                    <%--<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>--%>
                    <p class="card-text"><c:out value="${ad.description()}" /></p>
                </div>
                <div class="card-footer d-flex justify-content-between p-1">
                    <span>categories:
                        <c:if test="${ad.categories() != null}" >
                            <c:forEach var="category" items="${ad.categories()}" varStatus="i">
                                <c:out value="${category.concat(!i.last ? ', ': '')}" />
                            </c:forEach>
                        </c:if>
                    </span>
                    <span>created: <c:out value="${ad.created().toString()}"/></span>
                    <%--<button type="button" class="btn btn-success btn-sm">View</button>--%>
                </div>
            </div>
</div>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
