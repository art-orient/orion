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
<section id="orders" class="section">
    <div class="container">
        <h2 class="headline"><fmt:message key="ui.orders"/></h2>
    <c:if test="${numberOrders == 0}">
        <p><fmt:message key="ui.noOrders"/></p>
    </c:if>
    <c:if test="${numberOrders > 0}">
        <jsp:include page="paginator.jsp"/>
        <table id="orderHistory">
            <tr>
                <th><fmt:message key="ui.numberSign"/></th>
                <th><fmt:message key="ui.orderId"/></th>
                <th><fmt:message key="ui.orderDate"/></th>
                <th><fmt:message key="ui.products"/></th>
                <th><fmt:message key="ui.cost"/></th>
                <th><fmt:message key="ui.orderStatus"/></th>
            </tr>
            <c:forEach items="${orders}" var="order" varStatus="counter">
                <tr>
                    <td>${counter.count + offset}</td>
                    <td>ID${order.orderId}</td>
                    <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <table>
                            <c:forEach items="${order.productsAndQuantity}" var="product">
                                <tr>
                                    <td><c:out value="${product.key.modelName}"/></td>
                                    <td><img width="100px" height="75px"
                                             src="<c:out value="${product.key.imagePath}"/>"></td>
                                    <td> x ${product.value}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td>
                        <c:out value="${order.orderCost}"/>
                    </td>
                    <td>
                        <c:if test="${order.confirmed}">
                            <fmt:message key="ui.orderStatusConfirmed"/>
                        </c:if>
                        <c:if test="${!order.confirmed}">
                            <fmt:message key="ui.orderStatusAccepted"/>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="remove_order">
                                <input type="hidden" name="page" value="${page}"/>
                                <input type="hidden" name="order" value="${order.orderId}">
                                <input type="submit" value='<fmt:message key="ui.remove"/>'>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="paginator.jsp"/>
    </c:if>
    </div><br>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
