package by.ginel.dao.impl;

import by.ginel.dao.GenreDao;
import by.ginel.entity.Genre;
import by.ginel.entity.Genre_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreDaoImpl extends AbstractDaoImpl<Genre> implements GenreDao {

    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }

    @Override
    public Genre findByName(String name){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
        Root<Genre> root = cq.from(Genre.class);
        cq.select(root).where(cb.equal(root.get(Genre_.NAME), name));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
