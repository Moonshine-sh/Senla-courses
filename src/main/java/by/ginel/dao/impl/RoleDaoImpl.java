package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.RoleDao;
import by.ginel.entity.Role;
import by.ginel.mapper.RowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private static final String SELECT_ALL = "SELECT * FROM role";
    private static final String SELECT_BY_ID = "SELECT * FROM role WHERE id = ?";
    private static final String INSERT = "INSERT INTO role (name) VALUES (?)";
    private static final String DELETE = "DELETE FROM role WHERE id = ?";
    private static final String UPDATE = "UPDATE role SET name = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;
    private final RowMapper<Role> rowMapper;

    @Override
    public List<Role> getAll() throws SQLException, InterruptedException {
        try(PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_ALL)){
            return rowMapper.mapToEntityList(selectStatement.executeQuery());
        }
    }

    @Override
    public Role getById(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement getByIdStatement = getConnection().prepareStatement(SELECT_BY_ID)){
            getByIdStatement.setLong(1, id);
            return rowMapper.mapToEntity(getByIdStatement.executeQuery());
        }
    }

    @Override
    public Long save(Role entity) throws SQLException, InterruptedException {
        try(PreparedStatement insertStatement = getConnection().prepareStatement(INSERT)){
            fillStatement(entity, insertStatement);
            insertStatement.executeUpdate();
            return null;
        }
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement deleteStatement = getConnection().prepareStatement(DELETE)){
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        }
    }

    @Override
    public void update(Role entity) throws SQLException, InterruptedException {
        try(PreparedStatement updateStatement = getConnection().prepareStatement(UPDATE)){
            fillStatement(entity, updateStatement);
            updateStatement.setLong(2, entity.getId());
            updateStatement.executeUpdate();
        }
    }

    private void fillStatement(Role entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, entity.getName());
    }

    private Connection getConnection() throws InterruptedException {
        return connectionHandler.getConnection();
    }
}
