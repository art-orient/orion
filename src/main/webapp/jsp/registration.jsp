<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="ui.registration"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<br>
<h1><fmt:message key="ui.registration"/></h1>
<br>
<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="firstname"><fmt:message key="ui.firstname"/>: </label>
    <input type="text" name="firstname" id="firstname" value="${firstname}"><br>
    <label for="lastname"><fmt:message key="ui.lastname"/>:
        <input type="text" name="lastname" id="lastname" value="${lastname}"><br>
        <label for="email">e-mail: </label>
        <input type="text" name="email" id="email" value="${email}"><br>
        <input type="submit" name="signup" value="<fmt:message key="ui.signUp"/>">
</form>
<br><br>
<jsp:include page="footer.jsp"/>
</body>
</html>
