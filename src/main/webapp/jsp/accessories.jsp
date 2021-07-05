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
    <title><fmt:message key="ui.addProduct"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="products">
    <fmt:message key="ui.accessory"/><br>
    <table id="orderHistory">
        <tr>
            <th>No</th>
            <th>Name</th>
            <th>Photo</th>
            <th id="columnToLimit">description</th>
            <th>Cost</th>
            <th></th>
        </tr>
        <c:forEach items="${sessionScope.products}" var="product" varStatus="counter">
            <tr>
                <td>${counter.count + index}</td>
                <td><c:out value="${product.typeRu}"/> <c:out value="${product.productDetails.brand}"/>
                    <c:out value="${product.productDetails.modelName}"/><br>
                </td>
                <td>
                    <img id="pic1" width="180px" src="images/accessories/${product.productDetails.imgPath}">
                </td>
                <td><c:out value="${product.productDetails.descriptionRu}"/></td>
                <td>$<c:out value="${product.productDetails.cost}"/></td>
                <td>
                    <form method="get" action="controller">
                        <input type="hidden" name="product" value="${product.accessoryId}">
                        <input type="hidden" name="page" value="${page}"/>
                        <input type="hidden" name="command" value="add_product">
                        <button>Add to card</button>
<%--                        <button><fmt:message key="ui.addToCart"/></button>--%>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
