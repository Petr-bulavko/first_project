<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="" rel="stylesheet">
</head>
<%@include file="/jsp/locale.jsp" %>
<body>
<div class="">
    <div class="">
        <div class="">
            <fmt:message var="requestFrom" key="error.request.from"/>
            ${requestFrom}
            ${pageContext.errorData.requestURI}<br>

            <fmt:message var="statusCode" key="error.status"/>
            ${statusCode}
            ${pageContext.errorData.statusCode}<br>

            <fmt:message var="throwableText" key="error.throwable"/>
            ${throwableText}
            ${pageContext.errorData.throwable}<br>
        </div>
    </div>
</div>
</body>
</html>
