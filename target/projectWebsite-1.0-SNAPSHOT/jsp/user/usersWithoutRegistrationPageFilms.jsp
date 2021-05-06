<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li><a href="index.html">Main</a></li>
                <li class="selected"><a href="films.jsp">Films</a></li>
                <li><a href="#">Сериалы</a></li>
                <li><a href="rating.html">Movies rating</a></li>
                <li><a href="contact.html">Contacts</a></li>
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
                <form method="post" action="#" id="search_form">
                    <input type="search" name="search_field" placeholder="Your request"/>
                    <input type="submit" class="btn" value="Find"/>
                </form>

            </div>

            <div class="sidebar">

                <form method="post" action="${pageContext.request.contextPath}/mainController">
                    <%@include file="/jsp/login.jsp" %>
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

        <img src="../../img/inter.png">
        <img src="../../img/body.png">
        <div class="content">

            <c:forEach var="film" items="${requestScope.film}">
                <div class="info_film">
                    <img src="/img/inter.jpg">
                        ${film.description}
                    <div class="button"><a href="show.html">Watch</a></div>
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
