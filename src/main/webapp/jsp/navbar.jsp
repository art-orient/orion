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
            <a href="/controller?command=home"><img src="images/OrionLogo.PNG" alt="Logo of club Orion"></a>
        </div>
    <ul class="navbar">
        <c:if test="${sessionScope.current_page != null && sessionScope.current_page != 'index.jsp'}">
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="home"/>
                    <input type="submit" value='<fmt:message key="ui.home"/>'/>
                </form>
            </li>
        </c:if>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="shoes"/>
                <input type="hidden" name="page" value="1"/>
                <input type="submit" value='<fmt:message key="ui.shoes"/>'/>
            </form>
        </li>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="clothing"/>
                <input type="hidden" name="page" value="1"/>
                <input type="submit" value='<fmt:message key="ui.clothing"/>'/>
            </form>
        </li>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="accessories"/>
                <input type="hidden" name="page" value="1"/>
                <input type="submit" value='<fmt:message key="ui.accessory"/>'/>
            </form>
        </li>
        <c:if test="${sessionScope.username == null}">
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="login"/>
                    <input type="submit" value='<fmt:message key="ui.login"/>'/>
                </form>
            </li>
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="check_reg_status"/>
                    <input type="submit" value='<fmt:message key="ui.newUser"/>'/>
                </form>
            </li>
        </c:if>
        <li>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="cart"/>
                <input type="submit" value='<fmt:message key="ui.cart"/>'/>
            </form>
        </li>
        <c:if test="${sessionScope.username != null}">
            <li>
                <form action="controller" method="get">
                <input type="hidden" name="command" value="orders"/>
                <input type="submit" value='<fmt:message key="ui.orders"/>'/>
                </form>
            </li>
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="profile"/>
                    <input type="submit" value='<fmt:message key="ui.profile"/>'/>
                </form>
            </li>
            <li>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value='<fmt:message key="ui.logout"/>'/>
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
<br><br>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <table>
    <tr>
        <td>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="product_management"/>
                <input type="submit" value='<fmt:message key="ui.productManagement"/>'/>
            </form>
        </td>
        <td>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="product_management"/>
                <input type="submit" value='<fmt:message key="ui.userManagement"/>'/>
            </form>
        </td>
        <td>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="product_management"/>
                <input type="submit" value='<fmt:message key="ui.orderManagement"/>'/>
            </form>
        </td>
    </tr>
    </table>
</c:if>
<br>
</body>
</html>
