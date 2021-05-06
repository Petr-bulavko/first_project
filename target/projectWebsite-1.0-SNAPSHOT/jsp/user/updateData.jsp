<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<style>
    <%@include file='/resources/css/style.css' %>
</style>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>
<div class="main">
    <div class="header">
        <div class="logo">

            <div class="logo_text">

                <h1><a href="index.html">MovieMonster</a></h1>
                <h2>Cinema is our passion!</h2>

            </div>
        </div>

        <div class="menubar">
            <ul class="menu">
                <li><a href="index.html">Main</a></li>
                <li class="selected"><a href="films.html">Films</a></li>
                <li>
                    CЮДА
                </li>
                <li><a href="rating.html">Movies rating</a></li>
                <li><a href="contact.html">Contacts</a></li>
            </ul>
        </div>
    </div>
    <div class="site_content">
        <div class="content">
            <div class="info_film" style="height: 740px">
                <form method="post" action="${pageContext.request.contextPath}/mainController">
                    <fmt:message var="inputTitle" key="input.login.title"/>
                    <input type="hidden" name="command" value="update_user"/>


                    <div class="">
                        <fmt:message var="firstName" key="register.firstname"/>
                        <h2 for="firstName">${firstName}</h2>
                        <input type="text" id="firstName" name="firstName"
                               placeholder="${sessionScope.userData.firstName}" required
                               pattern="^[A-Z][a-z]{1,16}$|^[А-Я][а-яё]{1,16}$"
                               title="${inputTitle}" oninvalid="setCustomValidity(loc())"
                               oninput="setCustomValidity('')">
                    </div>
                    <div class="">
                        <fmt:message var="lastName" key="register.lastname"/>
                        <h2 for="lastName">${lastName}</h2>
                        <input type="text" id="lastName" name="lastName"
                               placeholder="${sessionScope.userData.lastName}" required
                               pattern="^[A-Z][a-z]{1,16}$|^[А-Я][а-яё]{1,16}$"
                               title="${inputTitle}" oninvalid="setCustomValidity(loc())"
                               oninput="setCustomValidity('')">
                        <div class="">

                        </div>
                    </div>


                    <div class="">
                        <h2 for="email">Email*</h2>
                        <div class="">
                            <div class="">
                                <span class="">@</span>
                            </div>
                            <input id="email" type="email" name="email"
                                   placeholder="${sessionScope.userData.email}"
                                   required
                                   pattern="^([\w-\.]{2,15})@((?:[\w-]{0,4}\.){0,1}\w[\w-]{0,3})\.([a-z]{2,6})$"
                                   title="${inputTitle}" oninvalid="setCustomValidity(loc())"
                                   oninput="setCustomValidity('')"/>
                        </div>
                    </div>
                    <div class="">
                        <fmt:message var="birthday" key="register.birthday"/>
                        <h2 for="birthday">${birthday}</h2>
                        <fmt:message var="birthdayPlaceholder" key="register.birthday.placeholder"/>
                        <input id="birthday" type="date" name="birthday" placeholder="${birthdayPlaceholder}"
                               required title="${inputTitle}" value="2019-01-01" min="1900 -01-01"
                               max="2019-01-01"
                               oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/><br/>
                    </div>

                    <h2>Введите логин и пароль для подтверждения изменения данных</h2>

                    <div class="">
                        <fmt:message key="login.label" var="login"/>
                        <h2 for="login">${login}</h2><br/>

                        <input id="login" type="text" name="login" placeholder="${sessionScope.user.login}"
                               required pattern="^[\w-]{4,31}$" title="${inputTitle}"
                               oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/><br/>
                    </div>
                    <div class="">
                        <fmt:message var="password" key="password.label"/>
                        <h2 for="password">${password}</h2>

                        <fmt:message var="passwordPlaceholder" key="password.placeholder"/>
                        <input id="password" type="password" name="password"
                               placeholder="${passwordPlaceholder}"
                               required pattern="^[A-Za-z]\w{3,31}$" title="${inputTitle}"
                               oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/>
                    </div>


                    <fmt:message var="updateButton" key="cabinet.update.data"/>
                    <input class="button" type="submit" name="upd" value="${updateButton}"/>

                </form>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>
            <a href="index.html">Main</a> |
            <a href="films.html">Films</a> |
            <a href="#">Сериалы</a> |
            <a href="rating.html">Movies rating</a> |
            <a href="contact.html">Contacts</a> |
        </p>
        <p>wh-db.com 2015</p>
    </div>
</div>
</body>
</html>
