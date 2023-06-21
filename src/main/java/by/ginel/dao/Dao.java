package by.ginel.dao;

import by.ginel.entity.AbstractEntity;
import by.ginel.util.Pageable;

import java.util.List;

public interface Dao<T extends AbstractEntity> {
    List<T> getAll(Pageable pageable);

    T getById(Long id);

    T save(T entity);

    void delete(Long id);

    T update(T entity);
}
