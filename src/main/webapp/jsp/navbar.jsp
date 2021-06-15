<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
    <title>NavBar</title>
</head>
<body>
<nav class="nav" role="navigation">
    <div class="container nav-elements">
        <div class="branding">
            <a href="/"><img src="images/OrionLogo.PNG" alt="Logo of club Orion"></a>
        </div>
    <ul class="navbar">
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="home"/>
                <input type="submit" value='<fmt:message key="ui.header.home"/>'/>
            </form>
        </li>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="searchShoes"/>
                <input type="submit" value='<fmt:message key="ui.header.shoes"/>'/>
            </form>
        </li>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="searchClothing"/>
                <input type="submit" value='<fmt:message key="ui.header.clothing"/>'/>
            </form>
        </li>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="searchAccessory"/>
                <input type="submit" value='<fmt:message key="ui.header.accessory"/>'/>
            </form>
        </li>
        <c:if test="${sessionScope.username == null}">
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="login"/>
                    <input type="submit" value='<fmt:message key="ui.header.login"/>'/>
                </form>
            </li>
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="registration"/>
                    <input type="submit" value='<fmt:message key="ui.header.newUser"/>'/>
                </form>
            </li>
        </c:if>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="cart"/>
                <input type="submit" value='<fmt:message key="ui.header.cart"/>'/>
            </form>
        </li>
        <c:if test="${sessionScope.username != null}">
            <li>
                <form action="controller" method="get">
                <input type="hidden" name="command" value="orders"/>
                <input type="submit" value='<fmt:message key="ui.header.orders"/>'/>
                </form>
            </li>
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value='<fmt:message key="ui.header.logout"/>'/>
                </form>
            </li>
        </c:if>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="language"/>
                <input type="hidden" name="language" value='<fmt:message key="ui.language"/>'/>
                <input type="submit" value="EN/RU"/>
            </form>
        </li>
    </ul>
    </div>
</nav>
</body>
</html>
