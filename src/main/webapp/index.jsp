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
    <form name="login">
    <table>
        <tr><td>Логин:</td><td><input type="text" name="txtusername" required placeholder="Логин" /></td></tr>
        <tr><td>Пароль:</td><td><input type="password" name="txtpassword" required placeholder="Пароль" /></td></tr>
        <tr>
            <td><input type="submit" name="submit" value="Вход" formaction="/signin" formmethod="post" /></td>
            <td><input type="submit" name="registration" value="Регистрация" formnovalidate formaction="/registration.jsp" formmethod="post" /></td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>
