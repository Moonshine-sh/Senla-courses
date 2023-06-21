package by.ginel.dao.impl;

import by.ginel.dao.GenreDao;
import by.ginel.entity.Genre;
import by.ginel.entity.Genre_;
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
public class GenreDaoImpl extends AbstractDaoImpl<Genre> implements GenreDao {

    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }

    @Override
    public Genre findByName(String name){
        log.info("Executing method findByName()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> cq = cb.createQuery(getEntityClass());
        Root<Genre> root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.get(Genre_.NAME), name));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
