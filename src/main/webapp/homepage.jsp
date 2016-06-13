<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница пользователя ${username}</title>
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
<h3>Добро пожаловать, ${firstname} ${lastname}</h3>

<div style="background-color:grey; color:white; padding:10px;">

    <p>
    <form name="headform">

            Логин:<input type="text"  name="txtusername" value="${username}" placeholder="Логин" />
            Пароль:<input type="password" name="txtpassword" value="${password}" placeholder="Пароль" />
            <input type="submit" name="submit" value="Удалить пользователя" formaction="/delete" formmethod="post" />
    <input name="submit" type="submit" value="Добавить нового" formaction="/index.jsp" formmethod="post" />
    <input name="submit" type="submit" value="Отобразить список" formaction="/userlist" formmethod="post" />
</div><br>
<table style="width:25%">

    <tr><td>Имя:</td><td><input type="text" name="txtfirstname" value="${firstname}" placeholder="Имя" /></td></tr>
    <tr><td>Фамилия:</td><td><input type="text" name="txtlastname" value="${lastname}" placeholder="Фамилия" /></td></tr>
</table>

<input type="submit" name="submit" value="Изменить" formaction="/update" formmethod="post" /></p>
</form>
</body>
</html>
