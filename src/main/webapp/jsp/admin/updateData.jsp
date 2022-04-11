<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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
                <li class="selected">
                    <%@ include file="/jsp/locale.jsp" %>
                </li>
            </ul>
        </div>
    </div>
    <div class="site_content">
        <div class="content">
            <div class="info_film" style="height: 740px">
                <form method="post" action="${pageContext.request.contextPath}/mainController">

                    <div class="">
                        <h2>Film name</h2>
                        <input type="text" id="filmName" name="filmName"
                               placeholder="${film.get().filmName}" required
                               pattern="^[A-Z][a-z]{1,16}$|^[А-Я][а-яё]{1,16}$"
                               oninvalid="setCustomValidity(loc())"
                               oninput="setCustomValidity('')">
                    </div>
                    <div class="">
                        <h2>Film Year</h2>
                        <select name="filmYear" value="${film.get().filmYear}" required>
                            <option value="2000"> 2000 </option>
                            <option value="2001"> 2001 </option>
                            <option value="2002"> 2002 </option>
                            <option value="2003"> 2003 </option>
                            <option value="2004"> 2004 </option>
                            <option value="2005"> 2005 </option>
                            <option value="2006"> 2006 </option>
                            <option value="2007"> 2007 </option>
                            <option value="2008"> 2008 </option>
                            <option value="2009"> 2009 </option>
                            <option value="2010"> 2010 </option>
                            <option value="2011"> 2011 </option>
                            <option value="2012"> 2012 </option>
                            <option value="2013"> 2013 </option>
                            <option value="2014"> 2014 </option>
                            <option value="2015"> 2015 </option>
                            <option value="2016"> 2016 </option>
                            <option value="2017"> 2017 </option>
                            <option value="2018"> 2018 </option>
                            <option value="2019"> 2019 </option>
                            <option value="2020"> 2020 </option>
                            <option value="2021"> 2021 </option>
                        </select><br><br>
                    </div>

                    <div class="">
                        <h2>Film Genre</h2>
                        <select name="filmGenre" value="${film.get().filmGenre}" required>
                            <option value="Drama"> Драма </option>
                            <option value="Comedy"> Комедия </option>
                            <option value="Romance"> Мелодрама </option>
                            <option value="Action"> Боевик </option>
                            <option value="Horror"> Ужасы </option>
                            <option value="Cartoon"> Мультфильм </option>
                            <option value="Science fiction"> Научная фантастика </option>
                            <option value="Documentary"> Документальный фильм </option>
                            <option value="Thriller"> Триллер </option>
                            <option value="Adventure"> Приключения </option>
                            <option value="Biography"> 	Биографиия </option>
                            <option value="family"> Семейный фильм </option>
                        </select><br><br>
                    </div>

                    <div class="">

                        <h2>Film Country</h2>
                        <select name="filmCountry" value="${film.get().filmCountry}" required>
                            <option value="USA"> США </option>
                            <option value="Germany"> Германия </option>
                            <option value="France"> Франция </option>
                            <option value="Belgium"> Бельгия </option>
                            <option value="Russia"> Россия </option>
                            <option value="Ukraine"> Украина </option>
                            <option value="India"> Индия </option>
                            <option value="UK"> Великобритания </option>
                        </select><br><br>
                    </div>

                    <div class="">
                        <h2>Description</h2>

                        <textarea inputmode="text" name="description" style="width: 580px; height: 200px;" required>${film.get().description}</textarea><br><br>

                    </div>

                    <input type="hidden" name="command" value="update_film"/>
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
