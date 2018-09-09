<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 07.09.2018
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование пользователя</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/change-user" method="post">
        <h3>Введите новые данные</h3>
        <table>
            <tr>
                <td>
                    <p>Имя</p>
                </td>
                <td>
                    <p>
                        <input type="text" style="border-radius: 10px" name="firstName">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Фамилия</p>
                </td>
                <td>
                    <p>
                        <input type="text" name="lastName">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Номер телефона</p>
                </td>
                <td>
                    <p>
                        <input type="text" name="number">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Email</p>
                </td>
                <td>
                    <p>
                        <input type="text" name="email">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Статус</p>
                </td>
                <td>
                    <p>
                        <input type="text" name="roleName">
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div align="center" >
                        <input type="submit" value="Применить">
                    </div>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
