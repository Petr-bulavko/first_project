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
    <title>Films</title>
    <meta name="description" content="Кино монстр - это портал о кино"/>
    <meta name="keywords" content="фильмы, фильмы онлайн, hd"/>
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
                    <%@include file="/jsp/locale.jsp" %>
                </li>
            </ul>
        </div>
    </div>

    <div class="site_content">

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
                <h2>News</h2>
                <span>31.02.2018</span>
                <p>We have begun to crunch advanced search</p>
                <a href="#">Read</a>
            </div>

            <div class="sidebar">
                <h2>Movies rating</h2>
                <ul>
                    <li><a href="show.html">Интерстеллар</a><span class="rating_sidebar">8.1</span></li>
                    <li><a href="#">Матрица</a><span class="rating_sidebar">8.0</span></li>
                    <li><a href="#">Безумный макс</a><span class="rating_sidebar">7.5</span></li>
                    <li><a href="#">Облачный фтлас</a><span class="rating_sidebar">7.8</span></li>
                </ul>
            </div>
        </div>

        <div class="content">
            <c:forEach var="film" items="${requestScope.allFilms}" varStatus="status">
                <div class="info_film">

                    <img src=${film.img}>
                        ${film.description}

                    <form method="get" action="${pageContext.request.contextPath}/mainController">
                        <input type="hidden" name="filmId" value="${film.filmId}">
                        <input type="hidden" name="command" value="find_film_for_show_without_registration">
                        <input class="button" type="submit" value="Watch">
                    </form>
                    </tr>
                </div>
            </c:forEach>
        </div>

        <%--
        Это чтобы сделать несколько страниц
        <span>
           <span class="pprev">←</span>
        </span>

        <span>
           <span>1</span>
           Поидее надо сделать еще 1 такую же страницу
           <a href="">2</a>
        </span>
           --%>

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
<script>
    function loc() {
        var lng = document.getElementById("language").value;
        if (lng === 'ru_RU') {
            return 'Неверный формат';
        } else {
            return 'Incorrect format';
        }
    }
</script>
</body>
</html>
