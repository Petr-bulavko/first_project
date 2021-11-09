<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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
                        <fmt:message var="addUser" key="admin.add.admin.header"/>
                        <input type="hidden" name="command" value="go_to_add_admin">
                        <input class="btn" type="submit" value="${addUser}">
                    </form>
                </li>
                <li class="selected">
                    <form method="post"
                          action="${pageContext.request.contextPath}/mainController">
                        <input type="hidden" name="command" value="all_films">
                        <input class="btn" type="submit" value="All Films">
                    </form>
                </li>
            </ul>
        </div>
    </div>
    <div class="site_content" style="width: 1000px;">
        <div class="sidebar_container">

            <div class="sidebar">

                <h2>Search</h2>
                <form method="post" action="${pageContext.request.contextPath}/mainController" id="filmName">
                    <input type="hidden" name="command" value="find_film_by_name_authorized"/>
                    <input type="search" name="filmName" placeholder="Your request"/>
                    <input type="submit" class="btn" value="Find"/>
                    <c:if test="${sessionScope.findFilmFailed eq 'true'}">
                        <fmt:message var="errorMessage" key="search.error.message"/>
                        ${errorMessage}
                    </c:if>
                </form>

            </div>

            <div class="sidebar">

                <h2><ctg:hello role="${sessionScope.user.login}"/></h2>

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

            <c:choose>
            <c:when test="${not empty requestScope.allUsersMap}">
            <div class="row">
                <fmt:message var="allUsers" key="admin.users.header"/>
                <h3 class="h3 text-center">${allUsers}</h3>
            </div>

            <table style="padding: 5%">
                <thead>
                <tr>
                    <th scope="col">№</th>
                    <fmt:message var="login" key="admin.users.login"/>
                    <th scope="col">${login}</th>
                    <fmt:message var="firstName" key="admin.users.firstname"/>
                    <th scope="col">${firstName}</th>
                    <fmt:message var="lastName" key="admin.users.lastname"/>
                    <th scope="col">${lastName}</th>
                    <th scope="col">Email</th>
                    <fmt:message var="birthday" key="admin.users.birthday"/>
                    <th scope="col">${birthday}</th>
                    <fmt:message var="userRole" key="admin.users.role"/>
                    <th scope="col">${userRole}</th>
                </tr>
                </thead>
            </table>

            <c:forEach var="user" items="${requestScope.allUsersMap}" varStatus="status">
                <div class="info_film" style="height: 100px;">
                    <table>
                    <tr>
                        <td>${status.count}</td>
                        <td>${user.key.login}</td>
                        <c:choose>
                            <c:when test="${user.key.role eq 'USER'}">
                                <td>${user.value.firstName}</td>
                                <td>${user.value.lastName}</td>
                                <td>${user.value.email}</td>
                                <fmt:parseDate value="${user.value.dateOfBirth}" var="dateOfBirth"
                                               type="date"
                                               dateStyle="short" pattern="yyyy-MM-dd"/>
                                <fmt:formatDate value="${dateOfBirth}" var="formatDateTime"
                                                pattern="dd.MM.yyyy"/>
                                <td>${formatDateTime}</td>
                            </c:when>
                            <c:otherwise>
                                <td>no data</td>
                                <td>no data</td>
                                <td>no data</td>
                                <td>no data</td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <c:if test="${user.key.role eq 'USER' }">
                                <fmt:message key="admin.users.role.user" var="userRole"/>
                                <c:out value="${userRole}"/>
                            </c:if>
                            <c:if test="${user.key.role eq 'ADMIN'}">
                                <fmt:message var="adminRole" key="admin.users.role.admin"/>
                                <c:out value="${adminRole}"/>
                            </c:if>

                        </td>
                    </table>

                        <form method="post" action="${pageContext.request.contextPath}/mainController">
                            <input type="hidden" name="command" value="delete_user">
                            <input type="hidden" name="userId" value="${user.key.userId}">
                            <fmt:message var="deleteUser" key="admin.users.delete"/>
                            <input class="button" type="submit" value="${deleteUser}">
                        </form>

                    </tr>
                </div>
            </c:forEach>

        </div>
        </c:when>
        <c:otherwise>
            <fmt:message var="emptyUsers" key="admin.users.empty"/>
            <h3 class="font-italic">${emptyUsers}</h3>
        </c:otherwise>
        </c:choose>

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
</div>

</body>
</html>

