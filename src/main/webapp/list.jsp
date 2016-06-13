<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome back dear ${username}</title>
    <style>
        table, th, td {
            border: 0px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<html>
<body>
<c:forEach items="${list}" var="entry">
    <c:forEach items="${entry}" var="user">
       <h3> ${user.getUsername()}</h3>
    </c:forEach>
</c:forEach><br>
</body>
</html>
