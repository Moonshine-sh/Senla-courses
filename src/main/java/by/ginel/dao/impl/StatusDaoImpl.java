package by.ginel.dao.impl;

import by.ginel.dao.StatusDao;
import by.ginel.entity.Status;
import by.ginel.entity.Status_;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StatusDaoImpl extends AbstractDaoImpl<Status> implements StatusDao {

    @Override
    protected Class<Status> getEntityClass() {
        return Status.class;
    }

    @Override
    public Status findByName(String name){
        log.info("Executing method findByName()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Status> cq = cb.createQuery(getEntityClass());
        Root<Status> root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.get(Status_.NAME), name));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
