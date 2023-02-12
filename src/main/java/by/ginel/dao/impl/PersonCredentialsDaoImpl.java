package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.PersonCredentialsDao;
import by.ginel.entity.PersonCredentials;
import by.ginel.mapper.RowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonCredentialsDaoImpl implements PersonCredentialsDao {
    private static final String SELECT_ALL = "select * from person_cred ";
    private static final String SELECT_BY_ID = "select * from person_cred where pc.id = ?";
    private static final String SELECT_BY_PERSON_ID = "select p.id pid, pc.id pcid, p.*, pc.* from person_cred pc inner join person p on p.id = pc.person_id where pc.person_id = ?";
    private static final String INSERT = "INSERT INTO person_cred (login, person_id, password) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM person_cred WHERE id = ?";
    private static final String UPDATE = "UPDATE person_cred SET login = ?, person_id = ?, password = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;
    private final RowMapper<PersonCredentials> rowMapper;

    @Override
    public List<PersonCredentials> getAll() throws SQLException, InterruptedException {
        try(PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_ALL)){
            return rowMapper.mapToEntityList(selectStatement.executeQuery());
        }
    }

    @Override
    public PersonCredentials getById(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement getByIdStatement = getConnection().prepareStatement(SELECT_BY_ID)){
            getByIdStatement.setLong(1, id);
            return rowMapper.mapToEntity(getByIdStatement.executeQuery());
        }
    }

    @Override
    public Long save(PersonCredentials entity) throws SQLException, InterruptedException {
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
    public void update(PersonCredentials entity) throws SQLException, InterruptedException {
        try(PreparedStatement updateStatement = getConnection().prepareStatement(UPDATE)){
            fillStatement(entity, updateStatement);
            updateStatement.setLong(4, entity.getId());
            updateStatement.executeUpdate();
        }
    }

    @Override
    public PersonCredentials getByPersonId(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement getByPersonIdStatement = getConnection().prepareStatement(SELECT_BY_PERSON_ID)){
            getByPersonIdStatement.setLong(1, id);
            return rowMapper.mapToEntity(getByPersonIdStatement.executeQuery());
        }
    }

    private void fillStatement(PersonCredentials entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, entity.getLogin());
        insertStatement.setLong(2, entity.getPersonId());
        insertStatement.setString(3, entity.getPassword());
    }

    private Connection getConnection() throws InterruptedException {
        return connectionHandler.getConnection();
    }
}
