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
    <title><fmt:message key="ui.cart"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<section id="orders" class="section">
    <div class="container">
        <h2 class="headline"><fmt:message key="ui.cart"/></h2>
        <c:choose>
            <c:when test="${cart.size() > 0}">
                <p>${orderStatus}</p>
                <table id="orderHistory">
                    <tr>
                        <th><fmt:message key="ui.no"/></th>
                        <th><fmt:message key="ui.name"/></th>
                        <th><fmt:message key="ui.image"/></th>
                        <th><fmt:message key="ui.cost"/></th>
                        <th><fmt:message key="ui.number"/></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${groupedCart}" var="product" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td width="200px">
                                <c:choose>
                                    <c:when test="${sessionScope.language == 'en'}">
                                        <c:out value="${product.key.typeEn} "/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${product.key.typeRu} "/>
                                    </c:otherwise>
                                </c:choose>
                                <c:out value="${product.key.productDetails.brand}"/>
                                <c:out value="${product.key.productDetails.modelName}"/><br>
                            </td>
                            <td width="20%">
                                <img src="${directory}${product.key.productDetails.imgPath}">
                            </td>
                            <td><c:out value="${product.key.productDetails.cost}"/></td>
                            <td><c:out value="${product.value}"/></td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="product" value="${product.key.accessoryId}">
                                    <input type="hidden" name="category" value="accessories">
                                    <input type="hidden" name="command" value="remove_product">
                                    <button><fmt:message key="ui.remove"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><fmt:message key="ui.totalCost"/></td>
                        <td>${cartTotalCost}</td>
                        <td></td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="make_order"/>
                                <input type="submit" value='<fmt:message key="ui.makeOrder"/>'/>
                            </form>
                        </td>
                    </tr>
                </table><br><br>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="ui.emptyCart"/></p>
            </c:otherwise>
        </c:choose>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
