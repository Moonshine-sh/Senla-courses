package by.ginel.mapper.impl;

import by.ginel.entity.PersonCredentials;
import by.ginel.mapper.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonCredentialsRowMapper extends RowMapper<PersonCredentials> {
    @Override
    public PersonCredentials mapRow(ResultSet resultSet) throws SQLException {
        return PersonCredentials.builder()
                .id(resultSet.getLong("pcid"))
                .login(resultSet.getString("login"))
                .personId(resultSet.getLong("person_id"))
                .password(resultSet.getString("password"))
                .build();
    }
}
