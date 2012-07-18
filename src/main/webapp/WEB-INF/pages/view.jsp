<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE HTML>

<html>
    <head>
        <title>Teepee - <c:out value="${key}"/></title>
    </head>
    <body>

        <h1>Teepee</h1>

        <c:forEach var="item" items="${items}">
            <c:out value="${item}"/>
        </c:forEach>

        <a href="/">Teepee Home</a>

    </body>
</html>