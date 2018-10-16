<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="editing" value="${!header['referer'].endsWith('create')}" scope="session" />

<html>
<head>
    <jsp:include page="../assests/head.jsp">
        <jsp:param name="title" value="Adlister" />
    </jsp:include>
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<div class="container" id="content">
    <c:choose>
        <c:when test="${editing}">
            <%--editing shown--%>
                <div class="card editing">
                    <div class="card-header">
                        Editing ad.
                    </div>
                    <div class="card-body">
                        <form method="post" action="">
                            <fieldset>
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <input type="text" class="form-control" id="title" name="title" minlength="5" maxlength="100" aria-describedby="titleHelp"
                                        value='<c:out value="${ad.title()}" />'>
                                    <small id="titleHelp" class="form-text text-muted"><span id="titleLength">100</span> characters remaining.</small>
                                    <small id="titleInfo" class="form-text text-warning d-none"></small>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea class="form-control" id="description" name="description" rows="5" minlength="15" maxlength="5000" aria-describedby="desHelp"
                                        ><c:out value="${ad.description()}"/></textarea>
                                    <small id="desHelp" class="form-text text-muted"><span id="desLength">5000</span> characters remaining.</small>
                                    <small id="desInfo" class="form-text text-warning d-none"></small>
                                </div>
                                <div class="form-group">
                                    <label for="categories">Categories</label>
                                    <select multiple class="form-control" id="categories" name="categories">
                                        <c:forEach var="category" items="${sessionScope.categories}" >
                                            <option value="${category.id()}"><c:out value="${category.name()}" /></option>
                                        </c:forEach>
                                    </select>
                                    <small id="catInfo" class="form-text text-warning d-none"></small>
                                </div>
                                <button type="submit" id="updateAd" class="btn btn-primary"
                                        data-container="body" data-toggle="popover" data-placement="bottom"
                                        data-content="Nothing to update" data-trigger=manual">
                                    Update
                                </button>
                                <button id="cancelBtn" class="btn btn-secondary">Cancel</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            <%--viewing hidden--%>
                <div class="card viewing d-none">
                    <div class="card-header">
                        <div class="row d-flex justify-content-between">
                            <div class="col-12">
                                <h2><c:out value="${ad.title()}" /></h2>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <p class="card-text"><c:out value="${ad.description()}" /></p></div>
                    <div class="card-footer d-flex justify-content-between align-items-end p-1">
                        <span class="w-25">categories:
                            <c:if test="${ad.categories() != null}" >
                                <c:forEach var="category" items="${ad.categories()}" varStatus="i">
                                    <c:out value="${category.concat(!i.last ? ', ': '')}" />
                                </c:forEach>
                            </c:if>
                        </span>

                        <span>created: <c:out value="${ad.created().toString()}"/></span>
                        <div>
                            <button type="button" class="btn btn-warning btn-sm" id="editBtn">Edit</button>
                            <button type="button" class="btn btn-danger btn-sm"
                                    data-toggle="modal" data-target="#confirmDeleteModal"
                                    data-title='<c:out value="${ad.title()}" />'
                                    data-id='<c:out value="${ad.id()}" />'>Delete</button>
                        </div>
                    </div>
                </div>
        </c:when>
        <c:otherwise>
            <%--viewing shown--%>
                <div class="card viewing">
                    <div class="card-header">
                        <div class="row d-flex justify-content-between">
                            <div class="col-12">
                                <h2><c:out value="${ad.title()}" /></h2>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <p class="card-text"><c:out value="${ad.description()}" /></p></div>
                    <div class="card-footer d-flex justify-content-between align-items-end p-1">
                        <span class="w-25">categories:
                            <c:if test="${ad.categories() != null}" >
                                <c:forEach var="category" items="${ad.categories()}" varStatus="i">
                                    <c:out value="${category.concat(!i.last ? ', ': '')}" />
                                </c:forEach>
                            </c:if>
                        </span>

                        <span>created: <c:out value="${ad.created().toString()}"/></span>
                            <div>
                                <button type="button" class="btn btn-warning btn-sm" id="editBtn">Edit</button>
                                <button type="button" class="btn btn-danger btn-sm"
                                        data-toggle="modal" data-target="#confirmDeleteModal"
                                        data-title='<c:out value="${ad.title()}" />'
                                        data-id='<c:out value="${ad.id()}" />'>Delete</button>
                            </div>
                    </div>
                </div>
            <%--editing hidden--%>
                <div class="card editing d-none">
                    <div class="card-header">
                        Editing ad.
                    </div>
                    <div class="card-body">
                        <form method="post" action="">
                            <fieldset>
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <input type="text" class="form-control" id="title" name="title" minlength="5" maxlength="100" aria-describedby="titleHelp"
                                           value='<c:out value="${ad.title()}" />'>
                                    <small id="titleHelp" class="form-text text-muted"><span id="titleLength">100</span> characters remaining.</small>
                                    <small id="titleInfo" class="form-text text-warning d-none"></small>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea class="form-control" id="description" name="description" rows="5" minlength="15" maxlength="5000" aria-describedby="desHelp"
                                        ><c:out value="${ad.description()}"/></textarea>
                                    <small id="desHelp" class="form-text text-muted"><span id="desLength">5000</span> characters remaining.</small>
                                    <small id="desInfo" class="form-text text-warning d-none"></small>
                                </div>
                                <div class="form-group">
                                    <label for="categories">Categories</label>
                                    <select multiple class="form-control" id="categories" name="categories">
                                        <c:forEach var="category" items="${sessionScope.categories}" >
                                            <option value="${category.id()}"><c:out value="${category.name()}" /></option>
                                        </c:forEach>
                                    </select>
                                    <small id="catInfo" class="form-text text-warning d-none"></small>
                                </div>
                                <button type="submit" id="updateAd" class="btn btn-primary"
                                        data-container="body" data-toggle="popover" data-placement="bottom"
                                        data-content="Nothing to update" data-trigger=manual">
                                    Update
                                </button>
                                <button type="submit" id="cancelBtn" class="btn btn-secondary">Cancel</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
        </c:otherwise>
    </c:choose>
</div>

<script>
    let temp = "";
    <c:if test="${ad.categories() != null}" >
    <c:forEach var="category" items="${ad.categories()}" varStatus="i">
    temp += "<c:out value="${category.concat(!i.last ? ',': '')}" />";
    </c:forEach>
    </c:if>

    const initialTitle = '<c:out value="${ad.title()}"/>';
    const initialDescription = '<c:out value="${ad.description()}"/>';
    const initialCategories = temp;
    const adID = '${sessionScope.ad.id()}'
</script>

<jsp:include page="/WEB-INF/ads/partials/deleteModal.jsp" />

<script src="/static/ads.js" ></script>

<script src="/static/editAd.js" ></script>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>
