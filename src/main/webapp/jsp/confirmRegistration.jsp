<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<%--<c:redirect url="controller?command=register_user">--%>
<%--    <c:param name="registrationStatus" value="${registrationStatus}"/>--%>
<%--</c:redirect>--%>
</html>
