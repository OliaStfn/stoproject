package servlets;

import DAO.DaoException;
import DAO.DaoFactory;
import DAO.GenericDao;
import DAO.mySQL.Factory;
import beans.Master;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(value = "/settings/masters", name = "MasterServlet")
public class MasterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        LocalDate bornDate = LocalDate.parse(request.getParameter("bornDate"));
        String address = request.getParameter("address");
        String passport = request.getParameter("passport");
        String phoneNum = request.getParameter("mobile");
        String post = request.getParameter("post");
        String workPlace = request.getParameter("work-place");

        Master master = new Master();
        master.setName(name);
        master.setSurname(surname);
        master.setDateOfBirth(bornDate);
        master.setAddress(address);
        master.setPassport(passport);
        master.setPhoneNumber(phoneNum);
        master.setPost(post);
        master.setWorkPlace(workPlace);

        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Master.class);
            master = (Master) dao.create(master);
        } catch (DaoException e) {
        }
        response.sendRedirect("/setting/masters");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory factory = new Factory();
        try {
            GenericDao dao = factory.getDao(factory.getConnection(), Master.class);
            ArrayList<Master> masters = (ArrayList<Master>) dao.readAll();
            HttpSession session = request.getSession();
            session.setAttribute("masters", masters);
        } catch (DaoException e) {
        }
        request.getRequestDispatcher("/masters.jsp").forward(request, response);
    }
}
