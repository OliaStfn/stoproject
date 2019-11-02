package servlets;

import DAO.DaoException;
import DAO.DaoFactory;
import DAO.GenericDao;
import DAO.mySQL.Factory;
import DAO.mySQL.OrderDao;
import beans.Customer;
import beans.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/settings/customers", name = "CustomerServlet")
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Customer.class);
            ArrayList<Customer> customers = (ArrayList<Customer>) dao.readAll();

            OrderDao orderDao = (OrderDao) factory.getDao(factory.getConnection(), Order.class);
            for (Customer customer : customers) {
                ArrayList<Order> orders = (ArrayList<Order>) orderDao.readByCustomerId(customer.getId());
                customer.setOrders(orders);
            }
            HttpSession session = request.getSession();
            session.setAttribute("customers", customers);
        } catch (DaoException e) {
        }
        request.getRequestDispatcher("/customers.jsp").forward(request, response);
    }
}
