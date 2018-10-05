<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 10/5/18
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Ads" />
    </jsp:include>
</head>
<body>
    <jsp:include page="../partials/navbar.jsp" />
    <div class="container">
        <ul>
            <c:forEach var="ad" items="${ads}">
                <li>
                    <label>${ad.title}</label>
                    <br>
                    <label>${ad.description}</label>
                </li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>
