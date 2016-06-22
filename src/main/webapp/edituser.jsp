<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"  language="java" %>
<html>
<head>
    <title>Welcome to the Edit User Page</title>
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
<div style="background-color:black; color:white;">
<h2>Welcome to the Edit User Page</h2>
</div>
<form name="login">
    <table align="center" style="background-color:grey; color:white; ">
        <tr><td>Логин:</td><td>${user.username}</td></tr>
        <tr><td>Последнее редактирование:</td><td>${user.creationDate}</td></tr>
        <tr><td>Старый пароль</td><td><input type="password" name="oldpassword" required placeholder="Старый пароль" /></td>
        <tr><td>Новый пароль</td><td><input type="password" name="password" required placeholder="Новый пароль" /></td>
        </tr>
        <tr><td><p></td></tr>
        <tr><td>Имя:</td><td><input type="text" name="firstname" required value="${user.firstName}" placeholder="Имя" /></td></tr>
        <tr><td>Фамилия:</td><td><input type="text" name="lastname" required value="${user.lastName}" placeholder="Фамилия" /></td></tr>
        <br>
        <tr>
            <td></td>
            <td>
            <input type="submit" name="submit" value="изменить" formaction="/edituser" formmethod="post" />
                <input type="button" name="back" value="назад" onClick="history.go(-1);return true;" />
            </td>
        </tr>
    </table>
</form>

</body>
</html>