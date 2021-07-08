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
    <title><fmt:message key="ui.clothing"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="products">
    <fmt:message key="ui.clothing"/> <fmt:message key="ui.foundProducts"/> ${numberProducts}<br>
<jsp:include page="paginator.jsp"/>
    <table id="orderHistory">
        <c:if test="${sessionScope.products.size() > 0}">
            <tr>
            <th><fmt:message key="ui.numberSign"/></th>
            <th><fmt:message key="ui.name"/></th>
            <th><fmt:message key="ui.color"/></th>
            <th><fmt:message key="ui.image"/></th>
            <th><fmt:message key="ui.description"/></th>
            <th><fmt:message key="ui.cost"/></th>
            <th></th>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.products}" var="product" varStatus="counter">
            <tr>
                <td>${counter.count + offset}</td>
                <td width="200px">
                    <c:choose>
                        <c:when test="${sessionScope.language == 'en'}">
                            <c:out value="${product.typeEn} "/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${product.typeRu} "/>
                        </c:otherwise>
                    </c:choose>
                    <c:out value="${product.productDetails.brand}"/>
                    <c:out value="${product.productDetails.modelName}"/><br>
                </td>
                <td>
<%--                    <c:out value="${product.color}"/>--%>
                </td>
                <td width="20%">
                    <img src="/images/${product.getCategory()}/${product.productDetails.imgPath}">
                </td>
                <td id="description" width="50%">
                    <c:choose>
                        <c:when test="${sessionScope.language == 'en'}">
                            <c:forEach items="${product.productDetails.descriptionEn}" var="str">
                                <p><c:out value="${str}"/></p>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${product.productDetails.descriptionRu}" var="str">
                                <p><c:out value="${str}"/></p>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><c:out value="${product.productDetails.cost}"/></td>
                <td>
                    <form method="get" action="controller">
                        <input type="hidden" name="product" value="${product.accessoryId}">
                        <input type="hidden" name="category" value="clothing">
                        <input type="hidden" name="page" value="${page}"/>
                        <input type="hidden" name="command" value="add_product">
                        <button><fmt:message key="ui.addToCart"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
<jsp:include page="paginator.jsp"/>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
