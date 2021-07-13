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
    <title><fmt:message key="ui.profile"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<section id="login" class="section">
    <div class="container">
        <h2 class="headline"><fmt:message key="ui.confirmationOrder"/></h2><br>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="orders"/>
            <input type="hidden" name="page" value="1"/>
            <input type="submit" value='<fmt:message key="ui.orders"/>'/>
        </form><br>
   </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
