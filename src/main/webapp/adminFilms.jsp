<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<h2>Админ фильмы</h2>
<p><a href='<c:url value="createFilm.jsp" />'>Create new</a></p>
<table>
    <tr>
        <th>Name</th>
        <th>Year</th>
        <th>Genre</th>
        <th>Country</th>
        <th></th>
    </tr>
    <c:forEach var="film" items="${requestScope.film}">

        <tr>
            <td>${film.filmName}</td>
            <td>${film.filmYear}</td>
            <td>${film.filmGenre}</td>
            <td>${film.filmCountry}</td>
            <td>
                <a href='<c:url value="/film/edit?id=${film.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="/film/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${film.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
