package by.ginel.mapper.impl;

import by.ginel.entity.Person;
import by.ginel.mapper.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonRowMapper extends RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .mobNum(resultSet.getString("mob_num"))
                .locked(resultSet.getBoolean("locked"))
                .enabled(resultSet.getBoolean("enabled"))
                .build();
    }
}
