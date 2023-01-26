package by.ginel.dao.impl;

import by.ginel.dao.BookItemDao;
import by.ginel.entity.BookItem;
import org.springframework.stereotype.Component;

@Component
public class BookItemDaoImpl extends DaoImpl<BookItem> implements BookItemDao {
    @Override
    protected Class<BookItem> getEntityClass() {
        return BookItem.class;
    }
}
