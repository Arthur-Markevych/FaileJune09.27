<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"  language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<form>Вы зашли в панель администрирования как ${user.username} <form><input type="submit" name="logout" value="Выход" formaction="/logout" formmethod="get" /></form></h3>


    <form name="form">
    <tr><td><input type="submit" name="submit" value="Отобразить список пользователей" formaction="/userlist" formmethod="post" /></td>
    <td><input type="submit" name="submit" value="Редактировать список" formaction="/edit" formmethod="post" /></td>
    <td><input type="submit" name="submit" value="Редактировать личные данные" formaction="/edituser.jsp" formmethod="get" /></td></tr>
    </form>
</body>
</html>
