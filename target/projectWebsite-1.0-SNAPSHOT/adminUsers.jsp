<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="/config/messages"/>
<%--<fmt:setLocale value="${sessionScope.lang}"/>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>

</head>
<body>
<%--<%@include file="nav_bar.jsp" %>
<%@include file="lang_bar.jsp" %>--%>
<h2>
    <fmt:message key="language.ru" />
</h2>
<h2>Users List</h2>
<p><a href='<c:url value="createUser.jsp" />'>Create new</a></p>
<table>
    <tr>
        <th>Name</th>
        <th>Mail</th>
        <th></th>
    </tr>
    <c:forEach var="users" items="${requestScope.users}">
        <tr>
            <td>${users.name}</td>
            <td>${users.mail}</td>
            <td>
                <a href='<c:url value="/user/edit?id=${users.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="/user/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${users.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>