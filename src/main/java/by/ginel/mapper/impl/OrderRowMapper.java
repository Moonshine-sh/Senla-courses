package by.ginel.mapper.impl;

import by.ginel.entity.Order;
import by.ginel.entity.Person;
import by.ginel.entity.Status;
import by.ginel.mapper.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderRowMapper extends RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) throws SQLException {
        Person person = Person.builder()
                .id(resultSet.getLong("p.id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .mobNum(resultSet.getString("mob_num"))
                .locked(resultSet.getBoolean("locked"))
                .enabled(resultSet.getBoolean("enabled"))
                .build();
        Status status = Status.builder()
                .id(resultSet.getLong("s.id"))
                .name(resultSet.getString("name"))
                .build();
        return Order.builder()
                .id(resultSet.getLong("o.id"))
                .date(resultSet.getTimestamp("date"))
                .status(status)
                .person(person)
                .price(resultSet.getBigDecimal("price"))
                .build();
    }
}
