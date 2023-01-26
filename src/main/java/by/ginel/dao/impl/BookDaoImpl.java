package by.ginel.dao.impl;

import by.ginel.dao.BookDao;
import by.ginel.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl extends DaoImpl<Book> implements BookDao {
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
