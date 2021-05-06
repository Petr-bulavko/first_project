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
                <li class="selected">
                    <%@ include file="/jsp/locale.jsp" %>
                </li>
                <li class="selected">
                    <form method="post"
                          action="${pageContext.request.contextPath}/mainController">
                        <fmt:message var="logout" key="logout.button"/>
                        <input type="hidden" name="command" value="logout">
                        <input class="btn" type="submit" value="${logout}">
                    </form>
                </li>
                <li class="selected">
                    <form method="post"
                          action="${pageContext.request.contextPath}/mainController">
                        <fmt:message var="users" key="admin.users.header"/>
                        <input type="hidden" name="command" value="all_users">
                        <input class="btn" type="submit" value="${users}">
                    </form>
                </li>
                <li class="selected">
                    <form method="post"
                          action="${pageContext.request.contextPath}/mainController">
                        <fmt:message var="addUser" key="admin.add.user.header"/>
                        <input type="hidden" name="command" value="go_to_add_user">
                        <input class="btn" type="submit" value="${addUser}">
                    </form>
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
                <h2><ctg:hello role="${sessionScope.user.login}"/></h2>

                <form method="get" action="${pageContext.request.contextPath}/jsp/user/updateData.jsp">
                    <input class="btn" type="submit" value="Edit">
                </form>

                <form method="post" action="${pageContext.request.contextPath}/mainController">
                    <fmt:message var="logout" key="logout.button"/>
                    <input type="hidden" name="command" value="logout">
                    <input class="btn" type="submit" value="${logout}">
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

            <c:forEach var="film" items="${requestScope.film}">
                <div class="info_film">
                    <img src="img/inter.png">
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

</body>
</html>

