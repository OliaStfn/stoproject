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

@WebServlet(value = "/services", name = "ServicesServlet")
public class ServicesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        double price = Double.parseDouble(request.getParameter("price"));
        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Service.class);
            Service service = (Service) dao.read(id);
            service.setPrice(price);
            dao.update(service);
            response.sendRedirect("/services");
        } catch (DaoException e) {
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Service.class);
            ArrayList<Service> services = (ArrayList<Service>) dao.readAll();
            HttpSession session = request.getSession();
            session.setAttribute("services", services);
        } catch (DaoException e) {
        }
        request.getRequestDispatcher("services.jsp").forward(request, response);
    }
}
