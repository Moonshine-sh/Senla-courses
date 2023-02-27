package by.ginel.dao;

import by.ginel.entity.AbstractEntity;

import java.util.List;

public interface Dao<T extends AbstractEntity> {
    List<T> getAll();

    T getById(Long id);

    T save(T entity);

    void delete(Long id);

    T update(T entity);
}
