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
<table id="t01">
    <tr>
        <th>Логин</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Дата регистрации</th>
    </tr>
    <c:forEach items="${list}" var="entry">
        <tr>
            <td>${entry.username}</td>
            <td>${entry.firstName}</td>
            <td>${entry.lastName}</td>
            <td>${entry.creationDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
