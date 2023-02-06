package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.StatusDao;
import by.ginel.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatusDaoImpl implements StatusDao {
    private static final String SELECT_ALL = "SELECT * FROM status";
    private static final String SELECT_BY_ID = "SELECT * FROM status WHERE id = ?";
    private static final String INSERT = "INSERT INTO status (name) VALUES (?)";
    private static final String DELETE = "DELETE FROM status WHERE id = ?";
    private static final String UPDATE = "UPDATE status SET name = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;

    @Override
    public List<Status> getAll() {
        return null;
    }

    @Override
    public Status getById(Long id) {
        return null;
    }

    @Override
    public Long save(Status entity) {

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Status entity) {

    }
}
