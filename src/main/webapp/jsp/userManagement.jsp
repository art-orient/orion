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
    <title><fmt:message key="ui.userManagement"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<section id="products" class="section">
    <div class="container">
        <br><h2><fmt:message key="ui.userManagement"/></h2><br>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="check_reg_status"/>
            <input type="submit" value='<fmt:message key="ui.addNewUser"/>'/>
        </form><br>
        <c:if test="${error != null}">
            <c:out value="${error}"/><br>
        </c:if>
        <jsp:include page="paginator.jsp"/>
        <table id="orderHistory">
            <tr>
                <th><fmt:message key="ui.numberSign"/></th>
                <th><fmt:message key="ui.username"/></th>
                <th><fmt:message key="ui.firstname"/></th>
                <th><fmt:message key="ui.lastname"/></th>
                <th><c:out value="email"/></th>
                <th><fmt:message key="ui.role"/></th>
                <th><fmt:message key="ui.active"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="user" varStatus="counter">
                <tr>
                    <td>${counter.count + offset}</td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <c:if test="${user.active == true}">
                            <fmt:message key="ui.yes"/>
                        </c:if>
                        <c:if test="${user.active == false}">
                            <fmt:message key="ui.no"/>
                        </c:if>
                    </td>
                    <td>
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="change_role">
                            <input type="hidden" name="page" value="${page}"/>
                            <input type="hidden" name="username" value="${user.username}">
                            <select name="role">
                                <option value="" style="display:none"><fmt:message key="ui.selectRole"/></option>
                                <option value="ADMIN"><c:out value="ADMIN"/></option>
                                <option value="MANAGER"><c:out value="MANAGER"/></option>
                                <option value="CUSTOMER"><c:out value="CUSTOMER"/></option>
                            </select><br>
                            <input type="submit" value='<fmt:message key="ui.changeRole"/>'>
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="page" value="${page}"/>
                            <input type="hidden" name="username" value="${user.username}">
                            <input type="hidden" name="command" value="change_activity">
                            <c:if test="${user.active == true}">
                                <input type="submit" value='<fmt:message key="ui.blockUser"/>'>
                            </c:if>
                            <c:if test="${user.active == false}">
                                <input type="submit" value='<fmt:message key="ui.unblockUser"/>'>
                            </c:if>
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="delete_user">
                            <input type="hidden" name="page" value="${page}"/>
                            <input type="hidden" name="username" value="${user.username}">
                            <input type="submit" value='<fmt:message key="ui.delete"/>'>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table><br>
    <jsp:include page="paginator.jsp"/>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
