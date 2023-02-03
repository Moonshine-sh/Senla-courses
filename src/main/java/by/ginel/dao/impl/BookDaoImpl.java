package by.ginel.dao.impl;

import by.ginel.dao.BookDao;
import by.ginel.dao.datasource.MockDataSource;
import by.ginel.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl extends DaoImpl<Book> implements BookDao {
    public BookDaoImpl(MockDataSource mockDataSource) {
        super(mockDataSource);
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
