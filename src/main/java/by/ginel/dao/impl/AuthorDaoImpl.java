package by.ginel.dao.impl;

import by.ginel.dao.AuthorDao;
import by.ginel.entity.Author;
import by.ginel.entity.Author_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl extends AbstractDaoImpl<Author> implements AuthorDao {

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    public Author findByName(String name) {
        log.info("Executing method findByName()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(getEntityClass());
        Root<Author> root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.get(Author_.NAME), name));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
