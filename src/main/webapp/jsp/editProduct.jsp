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
    <title><fmt:message key="ui.edit"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<section class="section">
    <div class="container">
        <h2 class="headline"><fmt:message key="ui.edit"/></h2><br>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="update_product"/>
            <input type="hidden" name="productId" value="${productId}">
            <input type="hidden" name="category" value="${category}">
            <label for="typeRu"><fmt:message key="ui.type"/> <fmt:message key="ui.russian"/></label><br/>
            <input type="text" minlength="2" maxlength="30" name="typeRu" id="typeRu" value="${product.typeRu}">
            <br>
            <label for="typeEn"><fmt:message key="ui.type"/>  <fmt:message key="ui.english"/></label><br/>
            <input type="text" minlength="2" maxlength="30" name="typeEn" id="typeEn" value="${product.typeEn}">
            <br>
            <label for="brand"><fmt:message key="ui.brand"/></label><br/>
            <input type="text" minlength="2" maxlength="30" name="brand" id="brand" value="${product.productDetails.brand}" required>
            <br>
            <label for="modelName"><fmt:message key="ui.modelName"/></label><br/>
            <input type="text" minlength="2" maxlength="50" name="modelName" id="modelName" value="${product.productDetails.modelName}" required>
            <br>
            <label for="descriptionRu"><fmt:message key="ui.description"/> <fmt:message key="ui.russian"/></label><br/>
            <textarea rows="12" cols="70" name="descriptionRu" id="descriptionRu"><c:out value="${descriptionRu}"/>
            </textarea>
            <br>
            <label for="descriptionEn"><fmt:message key="ui.description"/> <fmt:message key="ui.english"/></label><br/>
            <textarea rows="12" cols="70" name="descriptionEn" id="descriptionEn"><c:out value="${descriptionEn}"/>
            </textarea>
            <br>
            <label for="currentImage"><fmt:message key="ui.currentImage"/></label><br>
            <img id="currentImage" width="200px" src="images/${category}/${product.productDetails.imgPath}">
            <br>
            <label for="image"><fmt:message key="ui.changeImage"/></label>
            <input type="checkbox" name="changeImage" value="true"><br>
            <input type="file" accept=".someext,image/*" name="image" id="image">
            <br>
            <c:if test="${category == 'clothing' || category == 'shoes'}">
                <label for="color"><fmt:message key="ui.color"/></label><br/>
                <input type="text" name="color" id="color" value="${product.color}">
                <br>
            </c:if>
            <label for="cost"><fmt:message key="ui.cost"/></label><br/>
            <input type="number" min="00.01" max="999" step=".01" name="cost" id="cost" value="${product.productDetails.cost}" required>
            <br>
            <c:if test="${category == 'accessories'}">
                <label for="availability"><fmt:message key="ui.availability"/></label><br/>
                <input type="number" min="0" name="availability" id="availability" value="${product.availability}" required>
                <br>
            </c:if>
            <label><fmt:message key="ui.active"/>
                <input type="radio" name="active" value="true" ${product.productDetails.active ? 'checked' : ''}>
                <fmt:message key="ui.yes"/>
                <input type="radio" name="active" value="false" ${!product.productDetails.active ? 'checked' : ''}>
                <fmt:message key="ui.no"/>
            </label><br>
            <input type="submit" value='<fmt:message key="ui.edit"/>' id="submit">
            <br><br>
        </form><br>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
