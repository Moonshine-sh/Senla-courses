package by.ginel.dao.impl;

import by.ginel.dao.OrderDao;
import by.ginel.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl extends DaoImpl<Order> implements OrderDao {
    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }
}
