package servlets;

import DAO.DaoException;
import DAO.DaoFactory;
import DAO.GenericDao;
import DAO.mySQL.Factory;
import beans.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderDeleteServlet",value = "/orders/delete")
public class OrderDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Order.class);
            Order order = new Order();
            order.setId(Integer.parseInt(request.getParameter("id")));
            dao.delete(order);
        } catch (DaoException e) {
        }
        response.sendRedirect("/orders");
    }
}
