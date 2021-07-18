<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtag" prefix="mytag" %>
<c:if test="${sessionScope.language != null}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<footer class="footer">
    <div class="container">
    <div class="address">
        <div class="address-left">
            <fmt:message key="ui.ourAddress"/><br>
            <fmt:message key="ui.address"/><br>
            <fmt:message key="ui.cityAddress"/><br>
            <fmt:message key="ui.countryAddress"/><br>
            <c:out value="220025"/>
        </div>
        <div class="address-right">
            <fmt:message key="ui.ourPhones"/><br><br>
            <c:out value="+375-29-3721259"/><br><br>
            <c:out value="+375-29-7694238"/>
        </div>
        <br><br>
        <div class="author">
            <mytag:author/>
        </div>
    </div>
    </div>
</footer>
