package by.ginel.mapper;

import by.ginel.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class RowMapper<T extends AbstractEntity> {
    public T mapToEntity(ResultSet resultSet) throws SQLException{
        resultSet.next();
        return mapRow(resultSet);
    }

    public List<T> mapToEntityList(ResultSet resultSet) throws SQLException {
        List<T> entityList = new ArrayList<>();
        while(resultSet.next()){
            entityList.add(mapRow(resultSet));
        }
        return entityList;
    }

    protected abstract T mapRow(ResultSet resultSet) throws SQLException;
}
