<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/mainController">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="input.login.title" var="inputTitle"/>

    <fmt:message key="login.label" var="login"/>
    <h2 for="login">${login}</h2>

    <fmt:message key="login.placeholder" var="loginPlaceholder"/>
    <input id="login" type="text" name="login"
           placeholder="${loginPlaceholder}" required pattern="^[\w-]{4,31}$"
           oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/>

    <fmt:message key="password.label" var="password"/>
    <h2 for="password">${password}</h2>

    <fmt:message key="password.placeholder" var="passwordPlaceholder"/>
    <input id="password" type="password" name="password"
           placeholder="${passwordPlaceholder}" required pattern="^[A-Za-z]\w{3,31}$"
           oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/>

    <fmt:message key="login.button" var="loginButton"/>
    <input type="submit" class="btn" value="${loginButton}">

    <c:if test="${sessionScope.message eq 'true'}">
        <fmt:message var="errorMessage" key="login.error.message"/>
        ${errorMessage}
    </c:if>

    <div class="lables_passreg_text">
        <span><a href="#">Forgot your password?</a></span>

        <fmt:message var="updateButton" key="register.button"/>
        <span><a href="${pageContext.request.contextPath}/jsp/registration.jsp">${updateButton}</a></span>
    </div>

</form>
</body>
</html>
