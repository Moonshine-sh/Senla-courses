package by.ginel.dao.impl;

import by.ginel.dao.BookDao;
import by.ginel.entity.*;
import by.ginel.util.Pageable;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookDaoImpl extends AbstractDaoImpl<Book> implements BookDao {

    private static final String AUTHOR = "author";
    private static final String GENRE = "genre";
    private static final String NAME = "name";

    private final static Map<String, Function<Root<Book>, Path<String>>> paths = Map.of(
            AUTHOR, bookRoot -> bookRoot.join(Book_.AUTHORS).get(Author_.NAME),
            GENRE, bookRoot -> bookRoot.join(Book_.GENRES).get(Genre_.NAME),
            NAME, bookRoot -> bookRoot.get(Book_.NAME)
    );

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    private String getCriteriaLikeValue(String value) {
        return "%" + value.toLowerCase() + "%";
    }

    @Override
    public List<Book> getAll(Map<String, String> params, Pageable pageable) {
        log.info("Executing method getAll()");
        log.debug("Executing method getAll()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(getEntityClass());
        Root<Book> root = cq.from(getEntityClass());

        Predicate[] predicates = params.entrySet().stream().map(entry -> {
            Path<String> path = paths.get(entry.getKey()).apply(root);
            return cb.like(path, entry.getValue());
        }).toArray(Predicate[]::new);

        return entityManager.createQuery(cq.select(root).where(predicates))
                            .setFirstResult(pageable.getOffset())
                            .setMaxResults(pageable.getPageSize())
                            .getResultList();
    }

    @Override
    public List<Book> findAllByName(String name) {
        log.info("Executing method findAllByName()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(getEntityClass());
        Root<Book> root = cq.from(getEntityClass());
        cq.select(root).where(
                cb.or(
                        cb.like(root.get(Book_.NAME), getCriteriaLikeValue(name)),
                        cb.like(root.join(Book_.AUTHORS).get(Author_.NAME), getCriteriaLikeValue(name))
                )
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        log.info("Executing method findAllByAuthor()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(getEntityClass());
        Root<Book> root = cq.from(getEntityClass());

        cq.select(root).where(cb.equal(root.join(Book_.AUTHORS).get(Author_.NAME), author));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Book> findAllByGenre(Genre genre) {
        log.info("Executing method findAllByGenre()");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(getEntityClass());
        Root<Book> root = cq.from(getEntityClass());
        cq.select(root).where(cb.equal(root.join(Book_.GENRES).get(Genre_.NAME), genre));
        return entityManager.createQuery(cq).getResultList();
    }
}
