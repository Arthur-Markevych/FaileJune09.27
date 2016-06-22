<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"  language="java" %>
<html>
<head>
    <title>${title}</title>
    <style>
        table, th, td {
            border: 0px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
</head>
<body>
<h3>${title}</h3>
<div style="background-color: lime; color: black; width: 30%">
    <form name="user">
        <table>
            <tr><td>${user.username}</td></tr>
            <tr><td>${user.firstName}</td><td>${user.lastName}</td></tr>
            <tr>
                <td><input type="submit" name="registration" value="Выход" formaction="/logout" formmethod="get" /></td>
                <td><input type="submit" name="edituser" value="Редактировать профайл" formaction="/edituser.jsp" formmethod="get" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
