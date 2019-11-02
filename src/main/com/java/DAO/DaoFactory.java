package DAO;

public interface DaoFactory<T> {

    @FunctionalInterface
    public interface DaoCreator<T> {
        public GenericDao create(T connection);
    }

    public T getConnection() throws DaoException, DaoException;

    public GenericDao getDao(T connection, Class daoClass) throws DaoException;
}