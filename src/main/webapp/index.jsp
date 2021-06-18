<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a href="shoes" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/clothing.PNG" alt="Кроссовки">
                </div>
                <div class="catalog-name">Кроссовки</div>
            </a>
        </div>
        <div class="grid-cell">
            <a href="clothing" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/clothing.PNG" alt="Одежда">
                </div>
                <div class="catalog-name">Одежда</div>
            </a>
        </div>
        <div class="grid-cell">
            <a href="accessory" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/clothing.PNG" alt="Аксессуары">
                </div>
                <div class="catalog-name">Аксессуары</div>
            </a>
        </div>
        <div class="grid-cell">
            <a href="sale" class="catalog-item">
                <div class="catalog-img">
                    <img src="images/clothing.PNG" alt="Товары со скидкой">
                </div>
                <div class="catalog-name">Товары со скидкой</div>
            </a>
        </div>
    </div>
    </div>
    </div>
<br>
<jsp:include page="jsp/footer.jsp"/>
</body>
</html>