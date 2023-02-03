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
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {
    private final ConnectionHandler connectionHandler;
    private final RowMapper<Person> personRowMapper;
    private final RowMapper<Role> roleRowMapper;
    private final PersonCredentialsDao personCredentialsDao;

    private static final String SELECT_ALL = "SELECT * FROM person";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String INSERT = "INSERT INTO person (first_name, last_name, email, mob_num, locked, enabled) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM person WHERE id = ?";
    private static final String UPDATE = "UPDATE person SET first_name = ?, last_name = ?, email = ?, mob_num = ?, locked = ?, enabled = ? WHERE id = ?";

    private static final String GET_ROLES = "SELECT r.* FROM person p join person_role on person_id = p.id join role r on r.id = role_id WHERE p.id = ?";
    //private static final String GET_ORDERS = "select o.* from person p join \"order\" o on p.id = o.person_id join status s on o.status_id = s.id where p.id = ?";
    private static final String INSERT_PERSON_ROLES = "INSERT INTO person_role (person_id, role_id) VALUES (?, ?)";
    private static final String DELETE_PERSON_ROLES = "DELETE FROM person_role WHERE person_id = ?";


    @Override
    public List<Person> getAll() throws SQLException, InterruptedException {
        Statement selectStatement = getConnection().createStatement();

        ResultSet resultSet = selectStatement.executeQuery(SELECT_ALL);
        List<Person> people = new ArrayList<>();
        while(resultSet.next()){
            people.add(getPerson(resultSet));
        }
        return people;
    }

    @Override
    public Person getById(Long id) throws SQLException, InterruptedException {
        PreparedStatement getByIdStatement = getConnection().prepareStatement(SELECT_BY_ID);
        getByIdStatement.setLong(1, id);

        ResultSet resultSet = getByIdStatement.executeQuery();
        resultSet.next();
        return getPerson(resultSet);
    }

    @Override
    public Long save(Person entity) throws SQLException, InterruptedException {
        Connection connection = getConnection();
        PreparedStatement insertPerson = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        fillStatement(entity, insertPerson);
        insertPerson.executeUpdate();

        ResultSet generatedId = insertPerson.getGeneratedKeys();
        generatedId.next();
        Long id = generatedId.getLong(1);

        List<Role> roles = entity.getRoles();
        roles.forEach(role -> {
            try {
                PreparedStatement insertRole = connection.prepareStatement(INSERT_PERSON_ROLES);
                insertRole.setLong(1, id);
                insertRole.setLong(2, role.getId());
                insertRole.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return id;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Person entity) throws SQLException, InterruptedException {
        Connection connection = getConnection();
        PreparedStatement updatePerson = connection.prepareStatement(UPDATE);
        fillStatement(entity, updatePerson);
        updatePerson.setLong(7, entity.getId());
        updatePerson.executeUpdate();

        PreparedStatement deleteRoles = connection.prepareStatement(DELETE_PERSON_ROLES);
        deleteRoles.setLong(1, entity.getId());
        deleteRoles.executeUpdate();

        entity.getRoles().forEach(role -> {
            try {
                PreparedStatement insertRole = connection.prepareStatement(INSERT_PERSON_ROLES);
                insertRole.setLong(1, entity.getId());
                insertRole.setLong(2, role.getId());
                insertRole.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private Person getPerson(ResultSet resultSet) throws SQLException, InterruptedException {
        Person person = personRowMapper.mapToEntity(resultSet);
        person.setCredentials(personCredentialsDao.getByPersonId(person.getId()));

        PreparedStatement rolesStatement = getConnection().prepareStatement(GET_ROLES);
        rolesStatement.setLong(1, person.getId());

        ResultSet roleSet = rolesStatement.executeQuery();
        List<Role> roles = new ArrayList<>();
        while(roleSet.next()){
            roles.add(roleRowMapper.mapToEntity(roleSet));
        }
        person.setRoles(roles);

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
