package by.ginel.mapper.impl;

import by.ginel.entity.Role;
import by.ginel.mapper.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoleRowMapper extends RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet) throws SQLException {
        return Role.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
