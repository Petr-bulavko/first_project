<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty sessionScope.language ? sessionScope.language : en_US}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="localization.translation" scope="session"/>

<form method="post" class="" action="">
    <input type="hidden" name="command" value="change_language">
    <div class="">
        <select class="" id="language" name="language" onchange="submit()">
            <option value="en_US" ${language == "en_US" ? "selected" : ""}>English</option>
            <option value="ru_RU" ${language == "ru_RU" ? "selected" : ""}>Русский</option>
        </select>
    </div>
</form>
