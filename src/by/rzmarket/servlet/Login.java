package by.rzmarket.servlet;

import by.rzmarket.dto.LoginUserDto;
import by.rzmarket.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher("/WEB-INF/index.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginUserDto loginUserDto = LoginUserDto.builder()
                .email(req.getParameter("login"))
                .password(req.getParameter("password"))
                .build();
        System.out.println(UserService.getInstance().isValid(loginUserDto));
        resp.sendRedirect("/all-product");
    }
}
