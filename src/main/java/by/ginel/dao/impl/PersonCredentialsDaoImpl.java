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
    private final ConnectionHandler connectionHandler;
    private final RowMapper<PersonCredentials> rowMapper;

    private static final String SELECT_ALL = "select * from person_cred ";
    private static final String SELECT_BY_ID = "select * from person_cred where pc.id = ?";
    private static final String SELECT_BY_PERSON_ID = "select p.id pid, pc.id pcid, p.*, pc.* from person_cred pc join person p on p.id = pc.person_id where pc.person_id = ?";
    private static final String INSERT = "INSERT INTO person_cred (login, person_id, password) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM person_cred WHERE id = ?";
    private static final String UPDATE = "UPDATE person_cred SET login = ?, person_id = ?, password = ? WHERE id = ?";

    @Override
    public List<PersonCredentials> getAll() throws SQLException, InterruptedException {
        Statement selectStatement = getConnection().createStatement();

        ResultSet resultSet = selectStatement.executeQuery(SELECT_ALL);
        List<PersonCredentials> credentials = new ArrayList<>();
        while (resultSet.next()){
           credentials.add(rowMapper.mapToEntity(resultSet));
        }
        return credentials;
    }

    @Override
    public PersonCredentials getById(Long id) throws SQLException, InterruptedException {
        PreparedStatement getByIdStatement = getConnection().prepareStatement(SELECT_BY_ID);
        getByIdStatement.setLong(1, id);

        ResultSet resultSet = getByIdStatement.executeQuery();
        resultSet.next();
        return rowMapper.mapToEntity(resultSet);
    }

    @Override
    public Long save(PersonCredentials entity) throws SQLException, InterruptedException {
        PreparedStatement insertStatement = getConnection().prepareStatement(INSERT);
        fillStatement(entity, insertStatement);
        insertStatement.executeUpdate();
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        PreparedStatement deleteStatement = getConnection().prepareStatement(DELETE);
        deleteStatement.setLong(1, id);
        deleteStatement.executeUpdate();
    }

    @Override
    public void update(PersonCredentials entity) throws SQLException, InterruptedException {
        PreparedStatement updateStatement = getConnection().prepareStatement(UPDATE);
        fillStatement(entity, updateStatement);
        updateStatement.setLong(4, entity.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public PersonCredentials getByPersonId(Long id) throws SQLException, InterruptedException {
        PreparedStatement getByPersonIdStatement = getConnection().prepareStatement(SELECT_BY_PERSON_ID);
        getByPersonIdStatement.setLong(1, id);

        ResultSet resultSet = getByPersonIdStatement.executeQuery();
        resultSet.next();
        return rowMapper.mapToEntity(resultSet);
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
