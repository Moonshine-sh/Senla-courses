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
    private final ConnectionHandler connectionHandler;
    private final RowMapper<Order> rowMapper;

    private static final String SELECT_ALL = "SELECT * FROM \"order\" o join status s on s.id = o.status_id join person p on p.id = o.person_id";
    private static final String SELECT_BY_ID = "SELECT * FROM \"order\" o join status s on s.id = o.status_id join person p on p.id = o.person_id WHERE o.id = ?";
    private static final String INSERT = "INSERT INTO \"order\" (date, status_id, person_id, price) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM \"order\" WHERE id = ?";
    private static final String UPDATE = "UPDATE \"order\" SET date = ?, status_id = ?, person_id = ?, price = ? WHERE id = ?";

    @Override
    public List<Order> getAll() throws SQLException, InterruptedException {
        Statement selectStatement = getConnection().createStatement();

        ResultSet resultSet = selectStatement.executeQuery(SELECT_ALL);
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()){
            orders.add(rowMapper.mapToEntity(resultSet));
        }
        return orders;
    }

    @Override
    public Order getById(Long id) throws SQLException, InterruptedException {
        PreparedStatement getByIdStatement = getConnection().prepareStatement(SELECT_BY_ID);
        getByIdStatement.setLong(1, id);

        ResultSet resultSet = getByIdStatement.executeQuery();
        resultSet.next();
        return rowMapper.mapToEntity(resultSet);
    }

    @Override
    public Long save(Order entity) throws SQLException, InterruptedException {
        PreparedStatement insertStatement = getConnection().prepareStatement(INSERT);
        fillStatement(entity, insertStatement);
        insertStatement.executeUpdate();
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        PreparedStatement deleteStatement = getConnection().prepareStatement(DELETE);
        deleteStatement.setLong(1, id);
    }

    @Override
    public void update(Order entity) throws SQLException, InterruptedException {
        PreparedStatement updateStatement = getConnection().prepareStatement(UPDATE);
        fillStatement(entity, updateStatement);
        updateStatement.setLong(5, entity.getId());
        updateStatement.executeUpdate();
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
