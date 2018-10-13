<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
    <c:choose>
        <c:when test="${param.logged_in}">
                let hide = document.getElementsByClassName("user-logged-in");
        </c:when>
        <c:otherwise>
                let hide = document.getElementsByClassName("not-logged-in");

        </c:otherwise>
    </c:choose>

for(elem of hide) {
elem.style.display = "none";
}

document.addEventListener('DOMContentLoaded', function () {
    const navHeight = $('#navbar').outerHeight();
    $('#content').css("margin-top", "calc(" + navHeight + "px + 1rem)");
});
</script>
