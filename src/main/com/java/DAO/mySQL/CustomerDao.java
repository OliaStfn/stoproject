package DAO.mySQL;

import DAO.AbstractDao;
import DAO.DaoException;
import beans.Customer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerDao extends AbstractDao<Customer, Integer> {
    private static final Logger log = Logger.getLogger(AbstractDao.class);
    public CustomerDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Customer (phone_number, login, person_id) VALUES (?,?,(SELECT last_insert_id()));";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM Customer c JOIN Person USING(person_id) JOIN USER u ON c.login=u.login " +
                "WHERE customer_id=";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM Customer c JOIN Person USING(person_id) JOIN USER u ON c.login=u.login;";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Customer c JOIN Person p USING(person_id) JOIN User u ON c.login=u.login " +
                "SET c.phone_number=?, p.name=?, p.surname=?, p.date_of_birth=?," +
                " u.password=?, u.email=? WHERE customer_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Customer WHERE customer_id=?;";
    }

    @Override
    public Collection<Customer> parseResultSet(ResultSet resultSet) throws DaoException {
        Collection<Customer> customers = new ArrayList<Customer>();
        try {
            while (resultSet.next()) {
                Customer temp = new Customer();
                temp.setId(resultSet.getInt("customer_id"));
                temp.setPhoneNumber(resultSet.getString("phone_number"));
                temp.setLogin(resultSet.getString("login"));
                temp.setPassword(resultSet.getString("password"));
                temp.setEmail(resultSet.getString("email"));
                temp.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                temp.setName(resultSet.getString("name"));
                temp.setSurname(resultSet.getString("surname"));
                temp.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());

                customers.add(temp);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return customers;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Customer obj) throws DaoException {
        try {
            statement.setString(1, obj.getPhoneNumber());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getSurname());
            statement.setDate(4, Date.valueOf(obj.getDateOfBirth()));
            statement.setString(5, obj.getPassword());
            statement.setString(6, obj.getEmail());
            statement.setInt(7, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void statementInsert(PreparedStatement statement, Customer obj) throws DaoException {
        try {
            statement.setString(1, obj.getPhoneNumber());
            statement.setString(2, obj.getLogin());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer create(Customer object) throws DaoException {
        String query = "INSERT INTO User (login,password,email) VALUES (?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            int changedFields = statement.executeUpdate();
            if (changedFields != 1)
                throw new DaoException("During creating,created more or less than 1 object: " + changedFields);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        query = "INSERT INTO Person (name,surname,date_of_birth) VALUES (?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, object.getName());
            statement.setString(2,object.getSurname());
            statement.setDate(3,Date.valueOf(object.getDateOfBirth()));
            int changedFields = statement.executeUpdate();
            if (changedFields != 1)
                throw new DaoException("During creating,created more or less than 1 object: " + changedFields);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return super.create(object);
    }
}
