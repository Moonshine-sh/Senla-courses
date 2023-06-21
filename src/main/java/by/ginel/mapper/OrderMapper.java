package by.ginel.mapper;

import by.ginel.dao.PersonDao;
import by.ginel.dao.StatusDao;
import by.ginel.dto.OrderDto;
import by.ginel.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Autowired
    protected StatusDao statusDao;
    @Autowired
    protected PersonDao personDao;

    @Mappings({
            @Mapping(target = "status", expression = "java(statusDao.findByName(orderDto.getStatus()))"),
            @Mapping(target = "person", expression = "java(personDao.getById(orderDto.getPersonId()))")
    })
    public abstract Order mapToOrder(OrderDto orderDto);

    @Mappings({
            @Mapping(target = "status", expression = "java(order.getStatus().getName())"),
            @Mapping(target = "personId", expression = "java(order.getPerson().getId())")
    })

    public abstract OrderDto mapToOderDto(Order order);
}
