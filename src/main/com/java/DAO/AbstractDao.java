package DAO;


import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

public abstract class AbstractDao<T extends Identificator<PK>, PK extends Integer> implements GenericDao<T, PK> {
    protected Connection connection;
    private static final Logger log = Logger.getLogger(AbstractDao.class);

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getCreateQuery();

    public abstract String getSelectQuery();

    public abstract String getSelectAllQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract Collection<T> parseResultSet(ResultSet resultSet) throws DaoException;

    public abstract void statementUpdate(PreparedStatement statement, T obj) throws DaoException;

    public abstract void statementInsert(PreparedStatement statement, T obj) throws DaoException;


    @Override
    public T create(T object) throws DaoException {
        T tempObj;
        String query = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statementInsert(statement, object);
            int changedFields = statement.executeUpdate();
            if (changedFields != 1)
                throw new DaoException("During creating,created more or less than 1 object: " + changedFields);
        } catch (Exception e) {
            log.error(e);
            throw new DaoException(e);
        }
        query = getSelectQuery() + "(SELECT last_insert_id());";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            Collection<T> someList = parseResultSet(resultSet);
            if (someList == null || someList.size() != 1) {
                throw new DaoException("Error on search last created object");
            }
            tempObj = someList.iterator().next();

        } catch (Exception e) {
            log.error(e);
            throw new DaoException();
        }

        return tempObj;
    }

    @Override
    public T read(PK key) throws DaoException {
        Collection<T> someList;
        String query = getSelectQuery() + key + ";";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            someList = parseResultSet(resultSet);

        } catch (Exception e) {
            log.error(e + "Error with read database");
            throw new DaoException(e);
        }
        if (someList == null || someList.size() == 0) {
            log.error("Record with id=" + key + " not found in database");
            return null;
        }
        if (someList.size() > 1) {
            throw new DaoException("Found more than one record with id=" + key);
        }
        return someList.iterator().next();
    }

    @Override
    public void update(T obj) throws DaoException {
        String query = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statementUpdate(statement, obj);
            int changedFields = statement.executeUpdate();
        } catch (Exception e) {
            log.error(e);
            throw new DaoException();
        }
    }

    @Override
    public void delete(T obj) throws DaoException {
        String query = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, obj.getId());
            int changedFields = statement.executeUpdate();
            if (changedFields != 1) throw new DaoException("During query deleted more than 1 field: " + changedFields);
        } catch (Exception e) {
            log.error(e);
            throw new DaoException();
        }
    }

    @Override
    public Collection<T> readAll() throws DaoException {
        Collection<T> someList;
        String query = getSelectAllQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            someList = parseResultSet(resultSet);
        } catch (Exception e) {
            log.error(e + "Error with read databases");
            throw new DaoException();
        }
        return someList;
    }
}