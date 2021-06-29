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
        <h2><fmt:message key="ui.addProduct"/></h2><br>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="add_product_page">
            <select name="category">
                <option value="" style="display:none"><fmt:message key="ui.selectCategory"/></option>
                <option value="shoes"><fmt:message key="ui.addShoes"/></option>
                <option value="clothing"><fmt:message key="ui.addClothing"/></option>
                <option value="accessories"><fmt:message key="ui.addAccessory"/></option>
            </select>
            <button><fmt:message key="ui.addProduct"/></button>
        </form>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
