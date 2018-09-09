<%--
  Created by IntelliJ IDEA.
  User: Рома
  Date: 01.09.2018
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>RZ Market</title>
    </head>
    <body style="background-color: #cccccc">
        <%@ include file="WEB-INF/jsp/header.jsp"%>
        <br>
        <h1 style="margin-left: 40%; font-size: 300% ">Добро пожаловать</h1>
        <br>
        <form action="${pageContext.request.contextPath}/index" method="post">
            <table style="border: 0px solid black; margin-top: 100px; margin-left: 500px">
                <tr>
                    <td>
                       <p>Логин</p>
                    </td>
                    <td>
                        <input type="text" name="login">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Пароль</p>
                    </td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/registration">Регистрация</a>
                    </td>
                    <td>
                        <input type="submit" value="Войти">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="/all-products">Продолжить без регистрации</a>
                    </td>
                </tr>
            </table>
        </form>
        <%@ include file="WEB-INF/jsp/footer.jsp"%>
    </body>
</html>
