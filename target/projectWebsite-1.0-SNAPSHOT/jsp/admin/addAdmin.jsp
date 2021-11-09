<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-4">
    <div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <div class="row">
            <div class="col-4">
                <form class="form-signin text-center" method="post" action="${pageContext.request.contextPath}/mainController">
                    <input type="hidden" name="command" value="add_user"/>
                    <fmt:message var="inputTitle" key="input.login.title"/>

                    <fmt:message key="login.label" var="login"/>
                    <label class="h3 font-weight-normal" for="login">${login}</label><br/>

                    <fmt:message var="loginPlaceholder" key="login.placeholder"/>
                    <input class="form-control" id="login" type="text" name="login"
                           placeholder="${loginPlaceholder}" required pattern="^[\w-]{4,31}$"
                           title="${inputTitle}" oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/><br/>

                    <fmt:message var="password" key="password.label"/>
                    <label class="h3 font-weight-normal" for="password">${password}</label><br/>

                    <fmt:message var="passwordPlaceholder" key="password.placeholder"/>
                    <input class="form-control" id="password" type="password" name="password"
                           placeholder="${passwordPlaceholder}" required pattern="^[A-Za-z]\w{3,31}$"
                           title="${inputTitle}" oninvalid="setCustomValidity(loc())" oninput="setCustomValidity('')"/><br/>

                    <div class="form-group">
                        <select class="nav-item custom-select" name="role">
                            <c:forEach var="userRole" items="${requestScope.roles}" varStatus="status">
                                <c:if test="${userRole != 'USER'}">
                                    <option ${status.count eq 1 ? "selected" : ""} value="${userRole}">
                                        <c:if test="${userRole eq 'ADMIN'}">
                                            <fmt:message var="adminRole" key="admin.users.role.admin"/>
                                            <c:out value="${adminRole}"/>
                                        </c:if>
                                    </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <fmt:message key="admin.add.admin.header" var="addUserButton"/>
                    <button class="btn btn-lg btn-secondary btn-block" type="submit" action="${pageContext.request.contextPath}/jsp/admin/adminHeader.jsp">
                        <span data-feather="plus-circle"></span>
                        ${addUserButton}
                        <span class="sr-only">(current)</span>
                    </button>
                    <br/>

                    <c:if test="${sessionScope.message eq 'true'}">
                        <fmt:message var="errorMessage" key="login.error.message"/>
                        ${errorMessage}
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</main>
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
<c:remove var="message" scope="session"/>
</body>
</html>
