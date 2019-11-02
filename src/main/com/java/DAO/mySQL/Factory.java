package DAO.mySQL;

import DAO.DaoException;
import DAO.DaoFactory;
import DAO.GenericDao;
import beans.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Factory implements DaoFactory {
    private static final Logger log = Logger.getLogger(Factory.class);
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/sto?useSSL=false";
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    private Map<Class, DaoCreator> allDao;

    public Factory() {
        allDao = new HashMap<Class, DaoFactory.DaoCreator>();

        allDao.put(Admin.class, new DaoCreator() {
            @Override
            public GenericDao create(Object connection) {
                return new AdminDao((Connection) connection);
            }
        });

        allDao.put(Customer.class, new DaoCreator() {
            @Override
            public GenericDao create(Object connection) {
                return new CustomerDao((Connection) connection);
            }
        });

        allDao.put(Service.class,new DaoCreator() {
            @Override
            public GenericDao create(Object connection) {
                return new ServiceDao((Connection) connection);
            }
        });

        allDao.put(Order.class, new DaoCreator() {
            @Override
            public GenericDao create(Object connection) {
                return new OrderDao((Connection) connection);
            }
        });

        allDao.put(Master.class,new DaoCreator() {
            @Override
            public GenericDao create(Object connection) {
                return new MasterDao((Connection) connection);
            }
        });
        //allDao.put(Master.class, connection -> new MasterDao((Connection) connection))
    }

    @Override
    public Connection getConnection() throws DaoException {
        Connection connection = null;

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            log.error("Driver JDBC has NOT get");
            log.error(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("The successful connection for DB");
        } catch (SQLException e) {
            log.error("Failed connection for DB");
            log.error(e.getMessage());
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Object connection, Class daoClass) throws DaoException {
        DaoCreator creator = allDao.get(daoClass);
        if (creator == null){
            throw new DaoException("DAO for class "+daoClass+" not found");
        }
        return creator.create(connection);
    }
}