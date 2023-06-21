package by.ginel.dao.impl;

import by.ginel.dao.BookItemDao;
import by.ginel.entity.BookItem;
import by.ginel.entity.BookItem_;
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
public class BookItemDaoImpl extends AbstractDaoImpl<BookItem> implements BookItemDao {

    @Override
    protected Class<BookItem> getEntityClass() {
        return BookItem.class;
    }

    @Override
    public BookItem findFreeBookItemByBookId(Long bookId) {
        log.info("execute method findFreeBookItemByBookId() with id - {}", bookId);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookItem> cq = cb.createQuery(getEntityClass());
        Root<BookItem> root = cq.from(getEntityClass());
        cq.select(root).where(
                cb.and(
                        cb.equal(root.get(BookItem_.BOOK), bookId),
                        cb.isNull(root.get(BookItem_.ORDER))
                )
        );
        return entityManager.createQuery(cq)
                            .setFirstResult(0)
                            .setMaxResults(1)
                            .getSingleResult();
    }
}
