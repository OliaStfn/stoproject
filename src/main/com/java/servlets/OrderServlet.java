package servlets;

import DAO.DaoException;
import DAO.DaoFactory;
import DAO.GenericDao;
import DAO.mySQL.Factory;
import beans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

@WebServlet(value = "/orders", name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carBrand = request.getParameter("carBrand");
        String carModel = request.getParameter("carModel");
        String licensePlate = request.getParameter("licensePlate");
        String address = request.getParameter("address");
        int serviceId = Integer.parseInt(request.getParameter("services"));
        String description = request.getParameter("description");
        int masterId = 0;
        Customer customer = (Customer) request.getSession().getAttribute("user");
        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Master.class);
            ArrayList<Master> masters = (ArrayList<Master>) dao.readAll();
            boolean found = false;
            for (Master master : masters) {
                if (master.getStatus().equals(MasterStatus.FREE)) {
                    masterId = master.getId();
                    found = true;
                    break;
                }
            }
            if (!found) {
                request.setAttribute("message", "All masters are busy, please try later or " +
                        "call to the manager");
                request.getRequestDispatcher("/add-new-order.jsp").forward(request, response);
            }

            Order order = new Order();
            order.setCarBrand(carBrand);
            order.setCarModel(carModel);
            order.setLicensePlate(licensePlate);
            order.setReceptionPoint(address);
            order.setDescription(description);
            order.setMasterId(masterId);
            Service service = new Service();
            service.setId(serviceId);
            order.addService(service);
            order.setCustomerId(customer.getId());

            dao = factory.getDao(factory.getConnection(), Order.class);
            dao.create(order);
        } catch (DaoException e) {
        }
        response.sendRedirect("/orders");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory factory = new Factory();
        if (request.getParameterMap().containsKey("reception_point")) {
            String receptionPoint = request.getParameter("reception_point");
            try {
                GenericDao dao = factory.getDao(factory.getConnection(), Order.class);
                ArrayList<Order> orders = (ArrayList<Order>) dao.readAll();
                HashSet users = new HashSet();
                dao = factory.getDao(factory.getConnection(), Customer.class);
                for (Order order : orders) {
                    if (order.getReceptionPoint().equals(receptionPoint) &&
                            !order.getOrderDate().isBefore(LocalDate.now())) {
                        users.add((Customer) dao.read(order.getCustomerId()));
                    }
                }
                HttpSession session = request.getSession();
                session.setAttribute("customers", users);
                request.setAttribute("reception_point", receptionPoint);
                request.getRequestDispatcher("/customers-for-place-and-date.jsp").
                        forward(request, response);
            } catch (DaoException e) {
            }
        } else if (request.getParameterMap().containsKey("new")) {
            try {
                GenericDao dao = factory.getDao(factory.getConnection(), Service.class);
                ArrayList<Service> services = (ArrayList<Service>) dao.readAll();
                HttpSession session = request.getSession();
                HashSet<String> categories = new HashSet<>();
                for (Service service : services) {
                    categories.add(service.getCategory());
                }
                session.setAttribute("services", services);
                session.setAttribute("categories", categories);
            } catch (DaoException e) {
            }
            request.getRequestDispatcher("/add-new-order.jsp").forward(request, response);
        } else {
            try {
                GenericDao dao = factory.getDao(factory.getConnection(), Order.class);
                ArrayList<Order> orders = (ArrayList<Order>) dao.readAll();
                HttpSession session = request.getSession();
                if (session.getAttribute("userType").equals("customer")) {
                    Customer customer = (Customer) session.getAttribute("user");
                    for (Order order : orders) {
                        if (order.getCustomerId() != customer.getId())
                            orders.remove(order);
                    }
                }
                session.setAttribute("orders", orders);
            } catch (Exception e) {
                request.getRequestDispatcher("/authorization.jsp").forward(request, response);
            }
            request.setAttribute("message", "You are not logged!");
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        }
    }

}
