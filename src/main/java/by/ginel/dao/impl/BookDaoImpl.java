package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.BookDao;
import by.ginel.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final ConnectionHandler connectionHandler;


    private static final String SELECT_ALL = "SELECT * FROM book";
    private static final String SELECT_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String INSERT = "INSERT INTO book (name, description, pic_path) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM book WHERE id = ?";
    private static final String UPDATE = "UPDATE book SET name = ?, description = ?, pic_path = ? WHERE id = ?";

    @Override
    public List<Book> getAll() throws SQLException {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Long save(Book entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public void update(Book entity) {
    }
}
