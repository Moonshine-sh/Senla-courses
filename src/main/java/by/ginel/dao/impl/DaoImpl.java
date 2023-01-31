package by.ginel.dao.impl;

import by.ginel.dao.Dao;
import by.ginel.dao.datasource.MockDataSource;
import by.ginel.entity.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public abstract class DaoImpl<T extends AbstractEntity> implements Dao<T> {
    private final MockDataSource mockDataSource;

    protected abstract Class<T> getEntityClass();

    public List<T> getAll() {
        return mockDataSource.getAll(getEntityClass());
    }

    public T getById(Long id) {
        return mockDataSource.getById(getEntityClass(), id);
    }

    public T save(T entity) {
        return mockDataSource.save(entity);
    }

    public Long delete(Long id) {
        return mockDataSource.delete(getEntityClass(), id);
    }

    public T update(T entity) {
        return mockDataSource.update(entity);
    }
}