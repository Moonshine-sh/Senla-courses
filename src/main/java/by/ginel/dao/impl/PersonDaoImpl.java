package by.ginel.dao.impl;

import by.ginel.dao.PersonDao;
import by.ginel.entity.Person;
import by.ginel.entity.Person_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PersonDaoImpl extends AbstractDaoImpl<Person> implements PersonDao {

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    public List<Person> findAllByName(String name) {
        log.info("Executing method findAllByName()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(getEntityClass());
        Root<Person> root = cq.from(getEntityClass());
        cq.select(root).where(
                cb.or(
                        cb.equal(root.get(Person_.FIRST_NAME), name),
                        cb.equal(root.get(Person_.LAST_NAME), name)
                )
        );
        return entityManager.createQuery(cq).getResultList();
    }
}
