<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 07.09.2018
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация о пользователе</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user-info" method="post">
        <h3>Информация о пользователе</h3>
        <table>
            <tr>
                <td>Имя</td>
                <td>${sessionScope.user.firstName}</td>
            </tr>
            <tr>
                <td>Фамилия</td>
                <td>${sessionScope.user.lastName}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${sessionScope.user.email}</td>
            </tr>
            <tr>
                <td>Номер телефона</td>
                <td>${sessionScope.user.number}</td>
            </tr>
            <tr>
                <td>ID</td>
                <td>${sessionScope.user.id}</td>
            </tr>
            <tr>
                <td>Статус</td>
                <td>${sessionScope.user.role.name}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Редактировать">
                </td>
            </tr>
            <tr>
                <td colspan="2"> <a href="/get-user">Посмотреть другого пользователя</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
