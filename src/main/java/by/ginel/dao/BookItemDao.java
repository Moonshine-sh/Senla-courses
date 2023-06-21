package by.ginel.dao;

import by.ginel.entity.BookItem;

public interface BookItemDao extends Dao<BookItem>{
    BookItem findFreeBookItemByBookId(Long bookId);
}
