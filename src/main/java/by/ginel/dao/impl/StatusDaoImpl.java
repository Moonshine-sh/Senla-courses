package by.ginel.dao.impl;

import by.ginel.dao.StatusDao;
import by.ginel.entity.Status;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusDaoImpl extends AbstractDaoImpl<Status> implements StatusDao {

    @Override
    protected Class<Status> getEntityClass() {
        return Status.class;
    }

    @Override
    public Status findByName(String name){
        TypedQuery<Status> query = entityManager.createQuery("SELECT s FROM Status s WHERE s.name = :name", Status.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
