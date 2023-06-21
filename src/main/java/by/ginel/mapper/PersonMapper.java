package by.ginel.mapper;

import by.ginel.dao.OrderDao;
import by.ginel.dao.PersonCredentialsDao;
import by.ginel.dao.RoleDao;
import by.ginel.dto.PersonDto;
import by.ginel.entity.AbstractEntity;
import by.ginel.entity.Order;
import by.ginel.entity.Person;
import by.ginel.entity.Role;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", imports = {AbstractEntity.class, Role.class})
public abstract class PersonMapper {
    @Autowired
    protected PersonCredentialsDao personCredentialsDao;
    @Autowired
    protected RoleDao roleDao;
    @Autowired
    protected OrderDao orderDao;

    @Mappings({
            @Mapping(target = "credentials", expression = "java(personCredentialsDao.getById(personDto.getPersonCredentialsId()))"),
            @Mapping(target = "orders", expression = "java(personDto.getOrderIds().stream().map(orderDao::getById).toList())"),
            @Mapping(target = "roles", expression = "java(personDto.getRoles().stream().map(roleDao::findByName).toList())")
    })
    public abstract Person mapToPerson(PersonDto personDto);

    @Mappings({
            @Mapping(target = "personCredentialsId", expression = "java(person.getCredentials().getId())"),
            @Mapping(target = "roles", expression = "java(person.getRoles().stream().map(Role::getName).toList())"),
            @Mapping(target = "orderIds", expression = "java(person.getOrders().stream().map(AbstractEntity::getId).toList())")
    })
    public abstract PersonDto mapToPersonDto(Person person);
}
