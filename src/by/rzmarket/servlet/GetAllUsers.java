package by.rzmarket.servlet;

import by.rzmarket.entity.User;
import by.rzmarket.service.UserService;
import by.rzmarket.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/allusers")
public class GetAllUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspHelper.getPath("allusers"))
                .forward(req, resp);
        //        String value = UserService.getInstance().getAll().stream()
//                .map(this::toStringPresentation)
//                .collect(Collectors.joining());
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_16.name());
//        resp.getWriter().print(value);
    }

    private String toStringPresentation(User user) {
        return "<p>" + user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getNumber() + "</p>";
    }
}
