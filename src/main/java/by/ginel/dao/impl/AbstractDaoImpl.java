package by.ginel.dao.impl;

import by.ginel.dao.Dao;
import by.ginel.entity.AbstractEntity;
import by.ginel.entity.AbstractEntity_;
import by.ginel.util.Pageable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class AbstractDaoImpl<T extends AbstractEntity> implements Dao<T> {
    @PersistenceContext
    protected EntityManager entityManager;


    protected abstract Class<T> getEntityClass();

    @Override
    public List<T> getAll(Pageable pageable) {
        log.info("Executing method getAll()");
        log.debug("Executing method getAll()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getEntityClass());
        Root<T> from = query.from(getEntityClass());
        return entityManager.createQuery(query)
                            .setFirstResult(pageable.getOffset())
                            .setMaxResults(pageable.getPageSize())
                            .getResultList();
    }

    @Override
    public T getById(Long id) {
        log.info("Executing method getById()");
        log.debug("Executing method getById() for the entity with id: {}", id);

        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public T save(T entity) {
        log.info("Executing method save()");
        log.debug("Executing method save() for the entity: {}", entity);

        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        log.info("Executing method delete()");
        log.debug("Executing method delete() for the entity with id: {}", id);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> query = cb.createCriteriaDelete(getEntityClass());
        Root<T> root = query.from(getEntityClass());

        query.where(cb.equal(root.get(AbstractEntity_.ID), id));
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public T update(T entity) {
        log.info("Executing method update()");
        log.debug("Executing method update() for the entity: {}", entity);

        return entityManager.merge(entity);
    }
}
