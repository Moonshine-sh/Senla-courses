package by.ginel.mapper;

import by.ginel.dto.OrderDto;
import by.ginel.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order mapToOrder(OrderDto orderDto);

    OrderDto mapToOderDto(Order order);
}
