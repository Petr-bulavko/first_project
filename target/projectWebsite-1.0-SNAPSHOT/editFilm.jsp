<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Edit </title>
</head>
<body>
<h3>Edit film</h3>
<form method="post" action="/film/edit">
    <input type="hidden" value="${film.id}" name="id"/>
    <label>Name</label><br>
    <input name="filmName" value="${film.filmName}"/><br><br>
    <label>Age</label><br>
    <input name="filmYear" value="${film.filmYear}"/><br><br>
    <label>Age</label><br>
    <input name="filmGenre" value="${film.filmGenre}"/><br><br>
    <label>Age</label><br>
    <input name="filmCountry" value="${film.filmCountry}"/><br><br>
    <input type="submit" value="Send"/>
</form>
</body>
</html>
