package by.ginel.dao.impl;

import by.ginel.dao.OrderDao;
import by.ginel.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {

    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }
}
