package by.ginel.dao.impl;

import by.ginel.dao.OrderDao;
import by.ginel.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {

    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }
}
