package by.ginel.dao.impl;

import by.ginel.dao.BookItemDao;
import by.ginel.entity.BookItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookItemDaoImpl extends AbstractDaoImpl<BookItem> implements BookItemDao {

    @Override
    protected Class<BookItem> getEntityClass() {
        return BookItem.class;
    }
}
