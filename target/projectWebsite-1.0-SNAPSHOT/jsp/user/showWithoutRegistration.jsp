<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<style>
    <%@include file='/resources/css/style.css' %>
</style>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Фильм</title>
    <meta name="description" content="Кино монстр - это портал о кино"/>
    <meta name="keywords" content="фильмы, фильмы онлайн, hd"/>
</head>
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
                <li class="selected">
                    <%@ include file="/jsp/locale.jsp" %>
                </li>
            </ul>
        </div>
    </div>

    <div class="site_content" style="width: 920px;">

        <div class="sidebar_container">

            <div class="sidebar">

                <h2>Search</h2>
                <form method="post" action="${pageContext.request.contextPath}/mainController" id="filmName">
                    <input type="hidden" name="command" value="find_film_by_name_without_registration"/>
                    <input type="search" name="filmName" placeholder="Your request"/>
                    <input type="submit" class="btn" value="Find"/>
                    <c:if test="${sessionScope.findFilmFailed eq 'true'}">
                        <fmt:message var="errorMessage" key="search.error.message"/>
                        ${errorMessage}
                    </c:if>
                </form>

            </div>

            <div class="sidebar">

                <form method="post" action="${pageContext.request.contextPath}/mainController">
                    <%@include file="/WEB-INF/login.jsp" %>
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

        <div class="content" style="background-color: #f9f9f9;">
            <h1>${film.get().filmName}</h1>
            <iframe width="560" height="315" src="${film.get().linkMovie}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            <div class="info_film_page">
                <span class="label">Жанр:</span>${film.get().filmGenre}
                <span class="label">Год:</span>${film.get().filmYear}
                <span class="label">Страна:</span>${film.get().filmCountry}
            </div>

            <hr>

            <h2>Описание Фильма</h2>
            <div class="description_film">
                <img src=${film.get().img}>
                ${film.get().description}
            </div>
            <hr>

            <h2>Отзывы об Интерстеллар</h2>

            <div class="reviews"  style="width: 600px;">
                <div class="reviews">
                    <div class="review_name">
                        Сергей
                    </div>
                    <div class="review_text">
                        Отличный фильм, 3 часа пролетели незаметно.
                    </div>
                </div>
                <div class="reviews">
                    <div class="review_name">
                        Дмитрий
                    </div>
                    <div class="review_text">
                        После фильма Начало, я с не терпением ждал еще работ от Кристофера Нолана. Интерстеллар меня впечатлил.
                    </div>
                </div>
            </div>

            <div class="send"  style="width: 600px;">
                <form method="post" action="#" id="review">
                    <input type="text" name="review_name" placeholder="Ваше имя">
                    <textarea name="review_text" style="width: 600px;"></textarea>
                    <input type="submit" value="Отправить">
                </form>
            </div>

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