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
            Create an Ad.
        </div>
        <div class="card-body">
            <form method="post" action="/create">
                <fieldset>
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="title" name="title" minlength="5" maxlength="100" aria-describedby="titleHelp">
                        <small id="titleHelp" class="form-text text-muted"><span id="titleLength">100</span> characters remaining.</small>
                        <small id="titleInfo" class="form-text text-warning d-none"></small>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="5" minlength="15" maxlength="5000" aria-describedby="desHelp"></textarea>
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
                    <button type="submit" id="createAd" class="btn btn-primary">Create</button>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<script src="/static/createAd.js" ></script>

<jsp:include page="/WEB-INF/assests/scripts.jsp">
    <jsp:param name="logged_in" value="${sessionScope.logged_in}" />
</jsp:include>

</body>
</html>