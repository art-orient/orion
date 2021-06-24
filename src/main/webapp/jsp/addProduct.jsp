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

<section id="orders" class="section">
    <div class="container">
        <h2 class="headline"><fmt:message key="ui.addProduct"/></h2><br>
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="add_product"/>
            <label for="brand"><fmt:message key="ui.brand"/></label><br/>
            <input type="text" minlength="2" maxlength="30" name="brand" id="brand" required>
            <br>
            <label for="modelName"><fmt:message key="ui.modelName"/></label><br/>
            <input type="text" minlength="2" maxlength="50" name="modelName" id="modelName" required>
            <br>
            <label for="descriptionRu"><fmt:message key="ui.description"/> <fmt:message key="ui.russian"/></label><br/>
            <textarea rows="5" cols="25" name="descriptionRu" id="descriptionRu" required></textarea>
            <br>
            <label for="descriptionEn"><fmt:message key="ui.description"/> <fmt:message key="ui.english"/></label><br/>
            <textarea rows="5" cols="25" name="descriptionEn" id="descriptionEn" required></textarea>
            <br>
            <label for="image"><fmt:message key="ui.image"/></label><br/>
            <input type="file" accept="image/jpeg" name="image" id="image" required>
            <br>
            <label for="cost"><fmt:message key="ui.cost"/></label><br/>
            <input type="number" min="00.01" max="999" step=".01" name="cost" id="cost" required>
            <br>
            <label for="availability"><fmt:message key="ui.availability"/></label><br/>
            <input type="number" min="0" name="availability" id="availability" required>
            <br>
            <input type="submit" value='<fmt:message key="ui.addProduct"/>' id="submit">
            <br><br>
        </form><br>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
