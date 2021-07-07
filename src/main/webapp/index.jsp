<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orion - Index</title>
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="jsp/header.jsp"/>
<br>
    <div class="catalog">
    <div class="container">
    <div class="grid">
        <div class="grid-cell">
            <a href="/controller?command=shoes&page=1" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/asics.JPG" alt="<fmt:message key="ui.shoes"/>">
                </div>
                <div class="catalog-name"><fmt:message key="ui.shoes"/></div>
            </a>
        </div>
        <div class="grid-cell">
            <a href="/controller?command=clothing&page=1" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/noname.JPEG" alt="<fmt:message key="ui.clothing"/>">
                </div>
                <div class="catalog-name"><fmt:message key="ui.clothing"/></div>
            </a>
        </div>
        <div class="grid-cell">
            <a href="/controller?command=accessories&page=1" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/mizuno.jpg" alt="<fmt:message key="ui.accessory"/>">
                </div>
                <div class="catalog-name"><fmt:message key="ui.accessory"/></div>
            </a>
        </div>
        <div class="grid-cell">
            <a href="/controller?command=sale&page=1" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/saucony.JPG" alt="<fmt:message key="ui.sale"/>">
                </div>
                <div class="catalog-name"><fmt:message key="ui.sale"/></div>
            </a>
        </div>
    </div>
    </div>
    </div>
<br>
<jsp:include page="jsp/footer.jsp"/>
</body>
</html>