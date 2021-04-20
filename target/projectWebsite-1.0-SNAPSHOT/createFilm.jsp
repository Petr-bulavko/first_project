<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Фильмы</title>
</head>
<body>
<h3>New film</h3>
<form method="post" action="/film/create">
    <label>Name</label><br>
    <input name="filmName"/><br><br>
    <label>Year</label><br>
    <input name="filmYear"/><br><br>
    <label>Genre</label><br>
    <input name="filmGenre"/><br><br>
    <label>Country</label><br>
    <input name="filmCountry"/><br><br>
    <input type="submit" value="Save"/>
</form>
</body>
</html>
