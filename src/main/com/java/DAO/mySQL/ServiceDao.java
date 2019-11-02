package DAO.mySQL;

import DAO.AbstractDao;
import DAO.DaoException;
import beans.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ServiceDao extends AbstractDao<Service, Integer> {
    private static final Logger log = Logger.getLogger(AbstractDao.class);
    public ServiceDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Service (name,category,price,need_department) VALUES (?,?,?);";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM Service WHERE service_id=";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM Service;";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Service SET name=?,category=?,price=?, need_department=? WHERE service_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Service WHERE service_id=?;";
    }

    @Override
    public Collection<Service> parseResultSet(ResultSet resultSet) throws DaoException {
        Collection<Service> services = new ArrayList<Service>();

        try {
            while (resultSet.next()){
                Service temp = new Service();

                temp.setId(resultSet.getInt("service_id"));
                temp.setName(resultSet.getString("name"));
                temp.setCategory(resultSet.getString("category"));
                temp.setPrice(resultSet.getDouble("price"));
                temp.setNeedDepartment(resultSet.getBoolean("need_department"));

                services.add(temp);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return services;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Service obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getCategory());
            statement.setDouble(3, obj.getPrice());
            statement.setBoolean(4, obj.isNeedDepartment());
            statement.setInt(5, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Service obj) throws DaoException {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getCategory());
            statement.setDouble(3, obj.getPrice());
            statement.setBoolean(4,obj.isNeedDepartment());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
