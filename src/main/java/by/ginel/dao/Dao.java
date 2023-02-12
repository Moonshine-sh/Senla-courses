package by.ginel.dao;

import by.ginel.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends AbstractEntity> {
    List<T> getAll() throws SQLException, InterruptedException;
    T getById(Long id) throws SQLException, InterruptedException;
    T save(T entity) throws SQLException, InterruptedException;
    void delete(Long id) throws SQLException, InterruptedException;
    T update(T entity) throws SQLException, InterruptedException;
}
