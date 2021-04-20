<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit</title>
</head>
<body>
<h3>Edit user</h3>
<form method="post" action="/user/edit">
    <input type="hidden" value="${users.id}" name="id"/>
    <label>Name</label><br>
    <label>
        <input name="name" value="${users.name}"/>
    </label><br><br>
    <label>Mail</label><br>
    <label>
        <input name="mail" value="${users.mail}"/>
    </label><br><br>
    <label>Password</label><br>
    <label>
        <input name="password" value="${users.password}"/>
    </label><br><br>
    <input type="submit" value="Send"/>
</form>
</body>
</html>
