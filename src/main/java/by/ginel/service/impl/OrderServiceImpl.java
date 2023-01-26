package by.ginel.service.impl;

import by.ginel.dao.OrderDao;
import by.ginel.dto.OrderDto;
import by.ginel.mapper.OrderMapper;
import by.ginel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderDao orderDao;

    @Override
    public List<OrderDto> getAll() {
        return orderDao.getAll().stream().map(orderMapper::mapToOderDto).toList();
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.mapToOderDto(orderDao.getById(id));
    }

    @Override
    public OrderDto save(OrderDto entityDto) {
        return orderMapper.mapToOderDto(orderDao.save(orderMapper.mapToOrder(entityDto)));
    }

    @Override
    public Long delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public OrderDto update(OrderDto entityDto) {
        return orderMapper.mapToOderDto(orderDao.update(orderMapper.mapToOrder(entityDto)));
    }
}
