package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.BookItemDao;
import by.ginel.entity.BookItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookItemDaoImpl implements BookItemDao {
    private static final String SELECT_ALL = "SELECT * FROM book_item";
    private static final String SELECT_BY_ID = "SELECT * FROM book_item WHERE id = ?";
    private static final String INSERT = "INSERT INTO book_item (book_id, order_id, price) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM book_item WHERE id = ?";
    private static final String UPDATE = "UPDATE book_item SET book_id = ?, book_id = ?, price = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;

    @Override
    public List<BookItem> getAll() {
        return null;
    }

    @Override
    public BookItem getById(Long id) {
        return null;
    }

    @Override
    public Long save(BookItem entity) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(BookItem entity) {

    }
}
