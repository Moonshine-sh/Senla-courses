package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.GenreDao;
import by.ginel.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {
    private static final String SELECT_ALL = "SELECT * FROM genre";
    private static final String SELECT_BY_ID = "SELECT * FROM genre WHERE id = ?";
    private static final String INSERT = "INSERT INTO genre (name) VALUES (?)";
    private static final String DELETE = "DELETE FROM genre WHERE id = ?";
    private static final String UPDATE = "UPDATE genre SET name = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public Genre getById(Long id) {
        return null;
    }

    @Override
    public Long save(Genre entity) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Genre entity) {

    }
}
