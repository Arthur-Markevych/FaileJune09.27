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
<h2>Welcome to the ${title}</h2>
<div style="background-color:grey; color:white;">
    <form name="login" method="post" action="/submit">
        <table style="width:25%">
            <tr><td>Логин:</td><td><input type="text"  name="txtusername" placeholder="Логин" /></td></tr>
            <tr><td>Пароль</td><td><input type="password" name="txtpassword" placeholder="Пароль" /></td></tr>
            <tr><td><p></td></tr>
            <tr><td>Имя:</td><td><input type="text" name="txtfirstname" placeholder="Имя" /></td></tr>
            <tr><td>Фамилия:</td><td><input type="text" name="txtlastname" placeholder="Фамилия" /></td></tr>
            <br>
            <tr><td></td><td align="right"><input type="submit" name="submit" value="Добавить" /></td></tr>
        </table>
     </form>
</div>
</body>
</html>