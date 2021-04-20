<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<style>
    <%@include file='css/style.css' %>
</style>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Фильмы</title>
    <meta name="description" content="Кино монстр - это портал о кино"/>
    <meta name="keywords" content="фильмы, фильмы онлайн, hd"/>
<body>
<div class="main">

    <div class="header">

        <div class="logo">

            <div class="logo_text">

                <h1><a href="index.html">КиноМонстр</a></h1>
                <h2>Кино - наша страсть!</h2>

            </div>
        </div>

        <div class="menubar">
            <ul class="menu">
                <li><a href="index.html">Главная</a></li>
                <li class="selected"><a href="films.html">Фильмы</a></li>
                <li><a href="#">Сериалы</a></li>
                <li><a href="rating.html">Рейтинг фильмов</a></li>
                <li><a href="contact.html">Контакты</a></li>
            </ul>
        </div>
    </div>

    <div class="site_content">

        <div class="sidebar_container">

            <div class="sidebar">

                <h2>Поиск</h2>
                <form method="post" action="#" id="search_form" >
                    <input type="search" name="search_field" placeholder="Ваш запрос" />
                    <input type="submit" class="btn" value="Найти"/>
                </form>

            </div>

            <div class="sidebar">

                <h2>Вход</h2>
                <form method="post" action="#" id="login">
                    <input type="text" name="login_field" placeholder="Логин"/>
                    <input type="password" name="password_field" placeholder="Пароль"/>
                    <input type="submit" class="btn" value="Вход">

                    <div class="lables_passreg_text">
                        <span><a href="#">Забыли пароль?</a></span>
                        <span><a href="#">Регистрация</a></span>
                    </div>
                </form>

            </div>
            <div class="sidebar">
                <h2>Новости</h2>
                <span>31.02.2018</span>
                <p>Мы зарустили расширенный поиск</p>
                <a href="#">Читать</a>
            </div>

            <div class="sidebar">
                <h2>Рейтинг фильмов</h2>
                <ul>
                    <li><a href="show.html">Интерстеллар</a><span class="rating_sidebar">8.1</span></li>
                    <li><a href="#">Матрица</a><span class="rating_sidebar">8.0</span></li>
                    <li><a href="#">Безумный макс</a><span class="rating_sidebar">7.5</span></li>
                    <li><a href="#">Облачный фтлас</a><span class="rating_sidebar">7.8</span></li>
                </ul>
            </div>
        </div>


        <div class="content">
            <c:forEach var="film" items="${requestScope.film}">
            <div class="info_film">

                    ${film.filmName}
                <div class="button"><a href="show.html">Смотреть</a></div>
            </div>
            </c:forEach>
        </div>


    </div>
    <div class="footer">
        <p>
            <a href="index.html">Главная</a> |
            <a href="films.html">Фильмы</a> |
            <a href="#">Сериалы</a> |
            <a href="rating.html">Рейтинг фильмов</a> |
            <a href="contact.html">контакты</a> |
        </p>
        <p>wh-db.com 2015</p>
    </div>

</div>

</body>
</html>
