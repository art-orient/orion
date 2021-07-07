<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<table class="paginator">
    <tr>
        <c:if test="${page > 1}">
            <td>
            <form action="controller" method="get">
                <input type="hidden" value="1">
                <input type="submit" value='<c:out value="1"/>'>
            </form>
            </td>
            <td>
                <c:out value="${page - 1}"/>
            </td>
        </c:if>
        <td>
            <c:out value="${page} / ${numberPages}"/>
        </td>
        <c:if test="${page < numberPages}">
            <td>
                <c:out value="${page + 1}"/>
            </td>
            <td>
                <c:out value="${numberPages}"/>
            </td>
        </c:if>
    </tr>
</table>