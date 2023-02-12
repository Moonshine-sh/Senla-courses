package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.AuthorDao;
import by.ginel.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private static final String SELECT_ALL = "SELECT * FROM author";
    private static final String SELECT_BY_ID = "SELECT * FROM author WHERE id = ?";
    private static final String INSERT = "INSERT INTO author (name) VALUES (?)";
    private static final String DELETE = "DELETE FROM author WHERE id = ?";
    private static final String UPDATE = "UPDATE author SET name = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;

    @Override
    public List<Author> getAll() throws SQLException {
        return null;
    }

    @Override
    public Author getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public Long save(Author entity) throws SQLException {

        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Author entity) throws SQLException {

    }
}
