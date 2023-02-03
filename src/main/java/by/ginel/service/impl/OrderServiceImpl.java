package by.ginel.service.impl;

import by.ginel.dao.OrderDao;
import by.ginel.dto.OrderDto;
import by.ginel.mapper.OrderMapper;
import by.ginel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderDao orderDao;

    @Override
    public List<OrderDto> getAll() throws SQLException, InterruptedException {
        return orderDao.getAll().stream().map(orderMapper::mapToOderDto).toList();
    }

    @Override
    public OrderDto getById(Long id) throws SQLException, InterruptedException {
        return orderMapper.mapToOderDto(orderDao.getById(id));
    }

    @Override
    public Long save(OrderDto entityDto) throws SQLException, InterruptedException {
        orderDao.save(orderMapper.mapToOrder(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        orderDao.delete(id);
    }

    @Override
    public void update(OrderDto entityDto) throws SQLException, InterruptedException {
        orderDao.update(orderMapper.mapToOrder(entityDto));
    }
}
