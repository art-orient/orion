<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title><fmt:message key="ui.orders"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<section id="profile" class="section">
    <div class="container">
        <h2 class="headline"><fmt:message key="ui.orders"/></h2>
        <table id="profileTable">
            <tr>
                <td><fmt:message key="ui.username"/></td>
                <td><c:out value="${user.username}"/></td>
            </tr>
            <tr>
                <td><fmt:message key="ui.firstname"/></td>
                <td><c:out value="${user.firstName}"/></td>
            </tr>
            <tr>
                <td><fmt:message key="ui.lastname"/></td>
                <td><c:out value="${user.lastName}"/></td>
            </tr>
        </table>
    </div><br>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
