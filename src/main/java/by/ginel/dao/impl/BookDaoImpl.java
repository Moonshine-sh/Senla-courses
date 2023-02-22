package by.ginel.dao.impl;

import by.ginel.dao.BookDao;
import by.ginel.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookDaoImpl extends AbstractDaoImpl<Book> implements BookDao {

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
