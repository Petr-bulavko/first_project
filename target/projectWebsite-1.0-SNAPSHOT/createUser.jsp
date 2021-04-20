<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<h3>New user</h3>
<form method="post" action="/user/create">
    <label>Name</label><br>
    <input name="name"/><br><br>
    <label>Mail</label><br>
    <input name="mail"/><br><br>
    <label>Password</label><br>
    <input name="password"/><br><br>
    <input type="submit" value="Save"/>
</form>
</body>
</html>
