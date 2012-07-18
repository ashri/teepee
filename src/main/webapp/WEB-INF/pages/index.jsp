<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE HTML>

<html>
    <head>
        <title>Teepee</title>
    </head>
    <body>

        <h1>Teepee</h1>

        <p>No TaskPaper reference was provided.</p>

        <p>Please enter a reference or upload a new file for display</p>

        <h2>Load reference</h2>
        <form id="load-reference" action="" method="GET">
            <label for="key">Key</label>
            <input type="text" name="key" placeholder="Reference key" class="required">

            <input type="submit" name="loadReference" value="Load">
        </form>

    </body>
</html>