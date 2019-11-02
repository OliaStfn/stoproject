package servlets;

import DAO.DaoException;
import DAO.DaoFactory;
import DAO.GenericDao;
import DAO.mySQL.Factory;
import beans.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

@WebServlet(value = "/home", name = "Index")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Service.class);
            ArrayList<Service> services = (ArrayList<Service>) dao.readAll();
            HashSet<String> categories = new HashSet<>();
            for (Service service : services)
                categories.add(service.getCategory());
            HttpSession session = request.getSession();
            session.setAttribute("services", services);
            session.setAttribute("categories", categories);
        } catch (DaoException e) {
        }
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
