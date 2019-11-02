package DAO.mySQL;

import DAO.AbstractDao;
import DAO.DaoException;
import beans.Master;
import beans.MasterStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MasterDao extends AbstractDao<Master, Integer> {
    private static final Logger log = Logger.getLogger(AbstractDao.class);
    public MasterDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Master (post,work_place, address,phone_number,passport,person_id, " +
                "status_id) VALUES(?,?,?,?,?,(SELECT last_insert_id())," +
                "(SELECT status_id FROM Master_status WHERE status=?));";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM Master m JOIN Person USING(person_id) " +
                "JOIN Master_status ms ON m.status_id=ms.status_id " +
                "WHERE master_id=";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM Master m JOIN Person USING(person_id) " +
                "JOIN Master_status ms ON m.status_id=ms.status_id;";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE Master m JOIN Person p USING (person_id) SET m.post=?,m.work_place=?," +
                " m.address=?, m.phone_number=?,m.passport=?," +
                "m.status_id=(SELECT status_id FROM Master_status WHERE status=?)," +
                "p.name=?, p.surname=?, p.date_of_birth=? WHERE master_id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM Master WHERE master_id=?;";
    }

    @Override
    public Collection<Master> parseResultSet(ResultSet resultSet) throws DaoException {
        Collection<Master> masters = new ArrayList<Master>();
        try {
            while (resultSet.next()) {
                Master temp = new Master();

                temp.setId(resultSet.getInt("master_id"));
                temp.setPost(resultSet.getString("post"));
                temp.setWorkPlace(resultSet.getString("work_place"));
                temp.setAddress(resultSet.getString("address"));
                temp.setPhoneNumber(resultSet.getString("phone_number"));
                temp.setPassport(resultSet.getString("passport"));
                temp.setStatus(MasterStatus.valueOf(resultSet.getString("status")));
                temp.setName(resultSet.getString("name"));
                temp.setSurname(resultSet.getString("surname"));
                temp.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());

                masters.add(temp);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return masters;
    }

    @Override
    public void statementUpdate(PreparedStatement statement, Master obj) throws DaoException {
        try {
            statement.setString(1, obj.getPost());
            statement.setString(2, obj.getWorkPlace());
            statement.setString(3, obj.getAddress());
            statement.setString(4, obj.getPhoneNumber());
            statement.setString(5, obj.getPassport());
            statement.setString(6, obj.getStatus().toString());
            statement.setString(7, obj.getName());
            statement.setString(8, obj.getSurname());
            statement.setDate(9, Date.valueOf(obj.getDateOfBirth()));
            statement.setInt(10, obj.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void statementInsert(PreparedStatement statement, Master obj) throws DaoException {
        try {
            statement.setString(1, obj.getPost());
            statement.setString(2, obj.getWorkPlace());
            statement.setString(3, obj.getAddress());
            statement.setString(4, obj.getPhoneNumber());
            statement.setString(5, obj.getPassport());
            statement.setString(6, obj.getStatus().toString());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Master create(Master object) throws DaoException {
        String query = "INSERT INTO Person (name,surname,date_of_birth) VALUES (?,?,?);";
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
