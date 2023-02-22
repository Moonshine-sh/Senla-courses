package by.ginel.dao.impl;

import by.ginel.dao.Dao;
import by.ginel.entity.AbstractEntity;
import by.ginel.entity.AbstractEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDaoImpl<T extends AbstractEntity> implements Dao<T> {
    @PersistenceContext
    protected EntityManager entityManager;


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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> query = cb.createCriteriaDelete(getEntityClass());
        Root<T> root = query.from(getEntityClass());

        query.where(cb.equal(root.get(AbstractEntity_.ID), id));
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public T update(T entity) throws SQLException, InterruptedException {
        return entityManager.merge(entity);
    }
}
