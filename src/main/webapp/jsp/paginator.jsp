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
                <input type="hidden" name="command" value="${currentCommand}">
                <input type="hidden" name="page" value="1">
                <input type="submit" value='<c:out value="1"/>'>
            </form>
            </td>
        </c:if>
        <c:if test="${page > 4}">
            <td>
                <c:out value="..."/>
            </td>
        </c:if>
        <c:if test="${page > 3}">
            <td>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="${currentCommand}">
                    <input type="hidden" name="page" value="${page - 2}">
                    <input type="submit" value='<c:out value="${page - 2}"/>'>
                </form>
            </td>
        </c:if>
        <c:if test="${page > 2}">
            <td>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="${currentCommand}">
                    <input type="hidden" name="page" value="${page - 1}">
                    <input type="submit" value='<c:out value="${page - 1}"/>'>
                </form>
            </td>
        </c:if>
        <c:if test="${numberPages > 1}">
            <td>
                <c:out value="${page} / ${numberPages}"/>
            </td>
        </c:if>
        <c:if test="${page < numberPages - 1}">
            <td>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="${currentCommand}">
                    <input type="hidden" name="page" value="${page + 1}">
                    <input type="submit" value='<c:out value="${page + 1}"/>'>
                </form>
            </td>
        </c:if>
        <c:if test="${page < numberPages - 2}">
            <td>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="${currentCommand}">
                    <input type="hidden" name="page" value="${page + 2}">
                    <input type="submit" value='<c:out value="${page + 2}"/>'>
                </form>
            </td>
        </c:if>
        <c:if test="${page < numberPages - 3}">
            <td>
                <c:out value="..."/>
            </td>
        </c:if>
        <c:if test="${page < numberPages}">
            <td>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="${currentCommand}">
                    <input type="hidden" name="page" value="${numberPages}">
                    <input type="submit" value='<c:out value="${numberPages}"/>'>
                </form>
            </td>
        </c:if>
    </tr>
</table>