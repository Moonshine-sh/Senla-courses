package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.PersonCredentialsDao;
import by.ginel.dao.PersonDao;
import by.ginel.entity.Person;
import by.ginel.entity.Role;
import by.ginel.mapper.RowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {
    private static final String SELECT_ALL = "SELECT * FROM person";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String INSERT = "INSERT INTO person (first_name, last_name, email, mob_num, locked, enabled) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM person WHERE id = ?";
    private static final String UPDATE = "UPDATE person SET first_name = ?, last_name = ?, email = ?, mob_num = ?, locked = ?, enabled = ? WHERE id = ?";
    private static final String GET_ROLES = "SELECT r.* FROM person p inner join person_role on person_id = p.id inner join role r on r.id = role_id WHERE p.id = ?";
    //private static final String GET_ORDERS = "select o.* from person p join \"order\" o on p.id = o.person_id join status s on o.status_id = s.id where p.id = ?";
    private static final String INSERT_PERSON_ROLES = "INSERT INTO person_role (person_id, role_id) VALUES (?, ?)";
    private static final String DELETE_PERSON_ROLES = "DELETE FROM person_role WHERE person_id = ?";

    private final ConnectionHandler connectionHandler;
    private final RowMapper<Person> personRowMapper;
    private final RowMapper<Role> roleRowMapper;
    private final PersonCredentialsDao personCredentialsDao;

    @Override
    public List<Person> getAll() throws SQLException, InterruptedException {
        try(PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_ALL)){
            List<Person> people = personRowMapper.mapToEntityList(selectStatement.executeQuery());
            return people.stream().map(this::fillPerson).toList();
        }
    }

    @Override
    public Person getById(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement getByIdStatement = getConnection().prepareStatement(SELECT_BY_ID)){
            getByIdStatement.setLong(1, id);

            Person person = personRowMapper.mapToEntity(getByIdStatement.executeQuery());
            return fillPerson(person);
        }
    }

    @Override
    public Long save(Person entity) throws SQLException, InterruptedException {
        try(PreparedStatement insertPerson = getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            fillStatement(entity, insertPerson);
            insertPerson.executeUpdate();

            ResultSet generatedId = insertPerson.getGeneratedKeys();
            generatedId.next();
            entity.setId(generatedId.getLong(1));

            saveRoles(entity);
            return entity.getId();
        }
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Person entity) throws SQLException, InterruptedException {
        try(PreparedStatement updatePerson = getConnection().prepareStatement(UPDATE)){
            fillStatement(entity, updatePerson);
            updatePerson.setLong(7, entity.getId());
            updatePerson.executeUpdate();

            updateRoles(entity);
        }
    }

    private void updateRoles(Person entity) throws SQLException, InterruptedException {
        try(PreparedStatement deleteRoles = getConnection().prepareStatement(DELETE_PERSON_ROLES)){
            deleteRoles.setLong(1, entity.getId());
            deleteRoles.executeUpdate();

            saveRoles(entity);
        }
    }

    private void saveRoles(Person entity) {
        entity.getRoles().forEach(role -> {
            try (PreparedStatement insertRole = getConnection().prepareStatement(INSERT_PERSON_ROLES)){
                insertRole.setLong(1, entity.getId());
                insertRole.setLong(2, role.getId());
                insertRole.executeUpdate();
            } catch (SQLException | InterruptedException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private Person fillPerson(Person person){
        try(PreparedStatement rolesStatement = getConnection().prepareStatement(GET_ROLES)){
            person.setCredentials(personCredentialsDao.getByPersonId(person.getId()));

            rolesStatement.setLong(1, person.getId());

            List<Role> roles = roleRowMapper.mapToEntityList(rolesStatement.executeQuery());
            person.setRoles(roles);
        } catch (SQLException | InterruptedException e){
            e.printStackTrace();
        }
        return person;
    }

    private void fillStatement(Person entity, PreparedStatement updatePerson) throws SQLException {
        updatePerson.setString(1, entity.getFirstName());
        updatePerson.setString(2, entity.getLastName());
        updatePerson.setString(3, entity.getEmail());
        updatePerson.setString(4, entity.getMobNum());
        updatePerson.setBoolean(5, entity.getLocked());
        updatePerson.setBoolean(6, entity.getEnabled());
    }

    private Connection getConnection() throws InterruptedException {
        return connectionHandler.getConnection();
    }
}
