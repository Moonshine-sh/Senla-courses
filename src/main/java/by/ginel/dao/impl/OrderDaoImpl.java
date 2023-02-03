package by.ginel.dao.impl;

import by.ginel.dao.OrderDao;
import by.ginel.dao.datasource.MockDataSource;
import by.ginel.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl extends DaoImpl<Order> implements OrderDao {
    public OrderDaoImpl(MockDataSource mockDataSource) {
        super(mockDataSource);
    }

    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }
}
