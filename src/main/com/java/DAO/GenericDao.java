package DAO;

import java.util.Collection;

public interface GenericDao<T, PK> {
    T create(T object) throws DaoException;
    T read(PK key)throws DaoException;
    void update(T obj)throws DaoException;
    void delete(T obj)throws DaoException;
    Collection<T> readAll()throws DaoException;
}