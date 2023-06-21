package by.ginel.dao.impl;

import by.ginel.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Connection connection;

    public String findFirstNameByEmail(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select first_name from person p where p.email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("first_name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person findByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from person p where p.email = ?", Person.class, email);
    }
}
