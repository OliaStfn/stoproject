package DAO.mySQL;

import DAO.AbstractDao;
import DAO.DaoException;
import beans.Admin;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AdminDao extends AbstractDao<Admin, Integer> {
    private static final Logger log = Logger.getLogger(AbstractDao.class);
    public AdminDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Administrator (login) VALUES (?);";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM Administrator JOIN User USING(login) WHERE administrator_id=";//ON Administrator.login=User.login - INSTEAD OF USING
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM Administrator JOIN User USING(login);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Administrator a JOIN USER u USING(login) u.password=?, u.email=? WHERE a.administrator_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Administrator WHERE administrator_id=?;";
    }

    @Override
    public Collection<Admin> parseResultSet(ResultSet resultSet) throws DaoException {
        Collection<Admin> admins = new ArrayList<Admin>();
        try {
            while (resultSet.next()) {
                Admin temp = new Admin();
                temp.setId(resultSet.getInt("administrator_id"));
                temp.setLastAccess(resultSet.getTimestamp("last_access").toLocalDateTime());
                temp.setLogin(resultSet.getString("login"));
                temp.setPassword(resultSet.getString("password"));
                temp.setEmail(resultSet.getString("email"));
                temp.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());

                admins.add(temp);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return admins;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Admin obj) throws DaoException {
        try {
            statement.setString(1, obj.getPassword());
            statement.setString(2, obj.getEmail());
            statement.setInt(3, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Admin obj) throws DaoException {
        try {
            statement.setString(1, obj.getLogin());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Admin create(Admin object) throws DaoException {
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
        return super.create(object);
    }
}
