package by.ginel.dao.impl;

import by.ginel.config.ConnectionHandler;
import by.ginel.dao.OrderDao;
import by.ginel.entity.Order;
import by.ginel.mapper.RowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    private static final String SELECT_ALL = "SELECT * FROM \"order\" o inner join status s on s.id = o.status_id inner join person p on p.id = o.person_id";
    private static final String SELECT_BY_ID = "SELECT * FROM \"order\" o inner join status s on s.id = o.status_id inner join person p on p.id = o.person_id WHERE o.id = ?";
    private static final String INSERT = "INSERT INTO \"order\" (date, status_id, person_id, price) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM \"order\" WHERE id = ?";
    private static final String UPDATE = "UPDATE \"order\" SET date = ?, status_id = ?, person_id = ?, price = ? WHERE id = ?";

    private final ConnectionHandler connectionHandler;
    private final RowMapper<Order> rowMapper;

    @Override
    public List<Order> getAll() throws SQLException, InterruptedException {
        try(PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_ALL)){
            return rowMapper.mapToEntityList(selectStatement.executeQuery());
        }
    }

    @Override
    public Order getById(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement getByIdStatement = getConnection().prepareStatement(SELECT_BY_ID)){
            getByIdStatement.setLong(1, id);
            return rowMapper.mapToEntity(getByIdStatement.executeQuery());
        }
    }

    @Override
    public Long save(Order entity) throws SQLException, InterruptedException {
        try(PreparedStatement insertStatement = getConnection().prepareStatement(INSERT)){
            fillStatement(entity, insertStatement);
            insertStatement.executeUpdate();
            return null;
        }
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(PreparedStatement deleteStatement = getConnection().prepareStatement(DELETE)){
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        }
    }

    @Override
    public void update(Order entity) throws SQLException, InterruptedException {
        try(PreparedStatement updateStatement = getConnection().prepareStatement(UPDATE)){
            fillStatement(entity, updateStatement);
            updateStatement.setLong(5, entity.getId());
            updateStatement.executeUpdate();
        }
    }

    private void fillStatement(Order entity, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setTimestamp(1, entity.getDate());
        insertStatement.setLong(2, entity.getStatus().getId());
        insertStatement.setLong(3, entity.getPerson().getId());
        insertStatement.setBigDecimal(4, entity.getPrice());
    }

    private Connection getConnection() throws InterruptedException {
        return connectionHandler.getConnection();
    }
}
