package by.ginel.mapper;

import by.ginel.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends AbstractEntity> {
    T mapToEntity(ResultSet resultSet) throws SQLException;
}
