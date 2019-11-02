package DAO.mySQL;

import DAO.AbstractDao;
import DAO.DaoException;
import beans.Order;
import beans.OrderStatus;
import beans.Service;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDao extends AbstractDao<Order, Integer> {
    private static final Logger log = Logger.getLogger(AbstractDao.class);

    public OrderDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO sto.Order (car_brand,car_model,license_plate,description,reception_point,order_date," +
                "status_id,master_id,customer_id) " +
                "VALUES (?,?,?,?,?,?,(SELECT status_id FROM Order_status WHERE state=?),?,?);";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM sto.Order o LEFT JOIN Service_has_Order USING(order_id) " +
                "LEFT JOIN Service USING(service_id) JOIN Order_status s ON(o.status_id=s.status_id) WHERE order_id=";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM sto.Order o JOIN Service_has_Order USING(order_id)" +
                " JOIN Service USING(service_id) JOIN Order_status s ON(o.status_id=s.status_id);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE sto.Order SET car_brand=?,car_model=?,license_plate=?,description=?," +
                "reception_point=?,status_id=(SELECT status_id FROM Order_status WHERE state=?)," +
                "master_id=?,customer_id=? WHERE order_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM sto.Order WHERE order_id=?;";
    }

    @Override
    public Collection<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        Collection<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                boolean orderExist = false;
                Service service = new Service();

                Integer temp = resultSet.getInt("service_id");
                if (temp != 0) {
                    service.setId(resultSet.getInt("service_id"));
                    service.setName(resultSet.getString("name"));
                    service.setCategory(resultSet.getString("category"));
                    service.setPrice(resultSet.getDouble("price"));

                    for (Order item : orders) {
                        if (item.getId() == resultSet.getInt("order_id")) {
                            orderExist = true;
                            item.addService(service);
                        }
                    }
                }

                if (!orderExist) {
                    order.setId(resultSet.getInt("order_id"));
                    order.setCarBrand(resultSet.getString("car_brand"));
                    order.setCarModel(resultSet.getString("car_model"));
                    order.setLicensePlate(resultSet.getString("license_plate"));
                    order.setDescription(resultSet.getString("description"));
                    order.setReceptionPoint(resultSet.getString("reception_point"));
                    order.setOrderDate(resultSet.getDate("order_date").toLocalDate());
                    order.setStatus(OrderStatus.valueOf(resultSet.getString("state")));
                    order.setMasterId(resultSet.getInt("master_id"));
                    order.setCustomerId(resultSet.getInt("customer_id"));

                    if (temp != 0)
                        order.addService(service);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Order obj) throws DaoException {
        try {
            statement.setString(1, obj.getCarBrand());
            statement.setString(2, obj.getCarModel());
            statement.setString(3, obj.getLicensePlate());
            statement.setString(4, obj.getDescription());
            statement.setString(5, obj.getReceptionPoint());
            statement.setString(6, obj.getStatus().toString());
            statement.setInt(7, obj.getMasterId());
            statement.setInt(8, obj.getCustomerId());
            statement.setInt(9, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Order obj) throws DaoException {
        try {
            statement.setString(1, obj.getCarBrand());
            statement.setString(2, obj.getCarModel());
            statement.setString(3, obj.getLicensePlate());
            statement.setString(4, obj.getDescription());
            statement.setString(5, obj.getReceptionPoint());
            statement.setDate(6, Date.valueOf(obj.getOrderDate()));
            statement.setString(7, obj.getStatus().toString());
            statement.setInt(8, obj.getMasterId());
            statement.setInt(9, obj.getCustomerId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Order obj) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM sto.Service_has_Order WHERE order_id=?;")) {
            statement.setInt(1, obj.getId());
            statement.execute();
            try (PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
                preparedStatement.setInt(1, obj.getId());
                preparedStatement.execute();
            }
        } catch (Exception e) {
            log.error(e);
            throw new DaoException();
        }
    }

    @Override
    public Order create(Order object) throws DaoException {
        Order rezult = super.create(object);
        if (!(rezult.getServices() == null && object.getServices().isEmpty())) return rezult;
        int count = rezult.getServices().size();
        String query = "INSERT INTO Service_has_Order (service_id,order_id) VALUES ";
        for (Service service : rezult.getServices()) {
            count--;
            query += "(" + service.getId() + "," + rezult.getId() + ")";
            if (count != 0) query += ",";
        }
        query += ";";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (Exception e) {
            throw new DaoException(e);
        }
        rezult = read(rezult.getId());
        return rezult;
    }

    public Collection<Order> readByCustomerId(int id) throws DaoException {
        Collection<Order> someList;
        String query = "SELECT * FROM Order o JOIN Service_has_Order USING(order_id)" +
                " JOIN Service USING(service_id) JOIN Order_status s ON(o.status_id=s.status_id)" +
                " WHERE customer_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            someList = parseResultSet(resultSet);
        } catch (Exception e) {
            log.error(e + "Error with read databases");
            throw new DaoException();
        }
        return someList;
    }
}
