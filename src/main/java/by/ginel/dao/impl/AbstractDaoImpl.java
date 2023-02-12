package by.ginel.dao.impl;

import by.ginel.dao.Dao;
import by.ginel.entity.AbstractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
public abstract class AbstractDaoImpl<T extends AbstractEntity> implements Dao<T> {
    @PersistenceContext
    EntityManager entityManager;


    protected abstract Class<T> getEntityClass();

    @Override
    public List<T> getAll() throws SQLException, InterruptedException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getEntityClass());
        Root<T> from = query.from(getEntityClass());
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public T getById(Long id) throws SQLException, InterruptedException {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public T save(T entity) throws SQLException, InterruptedException {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        entityManager.remove(entityManager.find(getEntityClass(), id));
    }

    @Override
    public T update(T entity) throws SQLException, InterruptedException {
        return entityManager.merge(entity);
    }
}
