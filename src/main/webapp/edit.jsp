<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список пользователей</title>
    <style>
        table {
            width:100%;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
        table#t01 tr:nth-child(even) {
            background-color: #eee;
        }
        table#t01 tr:nth-child(odd) {
            background-color:#fff;
        }
        table#t01 th {
            background-color: black;
            color: white;
        }
    </style>
</head>
<html>
<body>
 <table id="t01" width="60%">
    <tr>
        <th>Логин</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Статус</th>
        <th>Последние изменения</th>
    </tr>
    <c:forEach items="${list}" var="entry">
        <form name="${entry.username}">
            <tr>
                <td><input type="hidden" name="username" value="${entry.username}" />${entry.username}</td>
                <td><input type="text" name="txtfirstname" value="${entry.firstName}" placeholder="${entry.firstName}" /></td>
                <td><input type="text" name="txtlastname" value="${entry.lastName}" placeholder="${entry.lastName}" /></td>
                <td>
                    <select  name="role">
                        <option value="${entry.roleId}">${entry.roleId}</option>
                        <option value="1">Администратор</option>
                        <option value="2">Пользователь</option>
                    </select>
                </td>
                <td>${entry.creationDate}</td>
                <td><input type="submit" formmethod="post" formaction="/update" value="изменить" /></td>
                <td><input type="submit" formmethod="post" formaction="/delete" value="удалить пользователя" /></td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>

