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
    <title><fmt:message key="ui.productManagement"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<section id="products" class="section">
    <div class="container">
        <form method="get" action="controller">
            <input type="hidden" name="command" value="add_product_page">
            <button><fmt:message key="ui.addShoes"/></button>
        </form>
        <form method="get" action="controller">
            <input type="hidden" name="command" value="add_product_page">
            <button><fmt:message key="ui.addClothing"/></button>
        </form>
        <form method="get" action="controller">
            <input type="hidden" name="command" value="add_product_page">
            <button><fmt:message key="ui.addAccessory"/></button>
        </form>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
