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
                <li>
                    <%@include file="../locale.jsp" %>
                </li>
            </ul>
        </div>
    </div>
    <div class="site_content">
        <div class="content">
            <div class="info_film" style="height: 645px">
                <form method="post" action="${pageContext.request.contextPath}/mainController">
                    <fmt:message var="inputTitle" key="input.login.title"/>
                    <input type="hidden" name="command" value="registration"/>


                    <div class="">
                        <fmt:message var="firstName" key="register.firstname"/>
                        <h2 for="firstName">${firstName}</h2>
                        <fmt:message var="firstNamePlaceholder" key="register.firstname.placeholder"/>
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               placeholder="${firstNamePlaceholder}" required
                               pattern="^[A-Z][a-z]{1,16}$|^[А-Я][а-яё]{1,16}$"
                               title="${inputTitle}" oninvalid="setCustomValidity(loc())"
                               oninput="setCustomValidity('')">
                    </div>
                    <div class="">
                        <fmt:message var="lastName" key="register.lastname"/>
                        <h2 for="lastName">${lastName}</h2>
                        <fmt:message var="lastNamePlaceholder" key="register.lastname.placeholder"/>
                        <input type="text" id="lastName" name="lastName"
                               placeholder="${lastNamePlaceholder}" required
                               pattern="^[A-Z][a-z]{1,16}$|^[А-Я][а-яё]{1,16}$"
                               title="${inputTitle}" oninvalid="setCustomValidity(loc())"
                               oninput="setCustomValidity('')">
                    </div>


                    <div class="">
                        <fmt:message key="login.label" var="login"/>
                        <h2 class="font-weight-normal" for="login">${login}</h2><br/>

                        <fmt:message var="loginPlaceholder" key="login.placeholder"/>
                        <input id="login" type="text" name="login" placeholder="${loginPlaceholder}"
                               required pattern="^[\w-]{4,31}$" title="${inputTitle}"
                               oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/><br/>
                    </div>
                    <div class="">
                        <fmt:message var="password" key="password.label"/>
                        <h2 class="font-weight-normal" for="password">${password}</h2>

                        <fmt:message var="passwordPlaceholder" key="password.placeholder"/>
                        <input id="password" type="password" name="password" placeholder="${passwordPlaceholder}"
                               required pattern="^[A-Za-z]\w{3,31}$" title="${inputTitle}"
                               oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/>
                    </div>


                    <div class="">
                        <fmt:message var="passwordPlaceholder" key="password.placeholder"/>
                        <h2 class="" for="email">Email*</h2>

                        <fmt:message var="emailPlaceholder" key="register.email.placeholder"/>
                        <input id="email" type="email" name="email" placeholder="${emailPlaceholder}"
                               required
                               pattern="^([\w-\.]{2,15})@((?:[\w-]{0,4}\.){0,1}\w[\w-]{0,3})\.([a-z]{2,6})$"
                               title="${inputTitle}" oninvalid="setCustomValidity(loc())"
                               oninput="setCustomValidity('')"/>

                    </div>
                    <div class="">
                        <fmt:message var="birthday" key="register.birthday"/>
                        <h2 class="" for="birthday">${birthday}</h2><br/>

                        <fmt:message var="birthdayPlaceholder" key="register.birthday.placeholder"/>
                        <input id="birthday" type="date" name="birthday" placeholder="${birthdayPlaceholder}"
                               required title="${inputTitle}" value="2019-01-01" min="1900 -01-01" max="2019-01-01"
                               oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/><br/>
                    </div>


                    <fmt:message var="updateButton" key="register.button"/>
                    <input class="button" type="submit" name="reg" value="${updateButton}"/>

                    <c:if test="${sessionScope.registrationMessage eq 'true'}">
                        <fmt:message var="errorMessage" key="registration.error.message"/>
                        <br>${errorMessage}
                    </c:if>
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
