<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>${param.title}</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:choose>
    <c:when test="${sessionScope.theme == 'default' || sessionScope.theme == null}">
        <link rel="stylesheet" href="/static/yeti.css" >
    </c:when>

    <c:otherwise>
        <link rel="stylesheet" href="/static/themes/${sessionScope.theme}.css">
    </c:otherwise>
</c:choose>
<script src="/static/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="/static/animate.min.css" rel="stylesheet">
<link rel="stylesheet" href="/static/style.css">