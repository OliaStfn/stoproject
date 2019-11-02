package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthorizationServlet", value = "/authorization")
public class AuthorizationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("type") &&
                request.getParameter("type").equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/authorization?type=login");
        } else
            request.getRequestDispatcher("authorization.jsp").forward(request, response);
    }
}