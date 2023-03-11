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
    public PersonCredentials findByUsername(String username){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonCredentials> cq = cb.createQuery(PersonCredentials.class);
        Root<PersonCredentials> root = cq.from(PersonCredentials.class);
        cq.select(root).where(cb.equal(root.get(PersonCredentials_.LOGIN), username));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
