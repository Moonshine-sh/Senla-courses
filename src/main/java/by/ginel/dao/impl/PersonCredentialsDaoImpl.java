package by.ginel.dao.impl;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.entity.Person;
import by.ginel.entity.PersonCredentials;
import by.ginel.entity.PersonCredentials_;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PersonCredentialsDaoImpl extends AbstractDaoImpl<PersonCredentials> implements PersonCredentialsDao {

    @Override
    protected Class<PersonCredentials> getEntityClass() {
        return PersonCredentials.class;
    }

    @Override
    public PersonCredentials getEntityWithFetchCriteria(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonCredentials> cq= cb.createQuery(PersonCredentials.class);
        Root<PersonCredentials> root = cq.from(PersonCredentials.class);
        root.fetch("person", JoinType.INNER);
        cq.select(root).where(cb.equal(root.get(PersonCredentials_.ID), id));

        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public PersonCredentials getEntityWithFetchJPQL(Long id){
        TypedQuery<PersonCredentials> query = entityManager.createQuery("select pc from PersonCredentials pc join fetch pc.person where pc.id = :id", PersonCredentials.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public PersonCredentials getEntityWithNamedGraph(Long id){
        EntityGraph entityGraph = entityManager.getEntityGraph("graph.PersonCredentials.person");

        Map hints = new HashMap<>();
        hints.put("jakarta.persistence.fetchgraph", entityGraph);

        return entityManager.find(getEntityClass(), id, hints);
    }
}
