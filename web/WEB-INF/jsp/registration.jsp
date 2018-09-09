<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 07.09.2018
  Time: 0:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <h2>Регистрация пользователя</h2>
        <h3>Введите Ваши данные</h3>
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
                    <p>Пароль</p>
                </td>
                <td>
                    <p>
                        <input type="password" name="password">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Повторите пароль</p>
                </td>
                <td>
                    <p>
                        <input type="password" name="passwordTwo">
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div align="center" >
                        <input type="submit" style="border-radius: 10px; background-color: black; color: darkorange" value="Зарегистрироваться">
                    </div>

                </td>
            </tr>
        </table>
    </form>
</body>
</html>
