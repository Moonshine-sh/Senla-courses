package by.ginel.mapper;

import by.ginel.dao.PersonDao;
import by.ginel.dto.PersonCredentialsDto;
import by.ginel.entity.Person;
import by.ginel.entity.PersonCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@Mapper(componentModel = "spring")
public abstract class PersonCredentialsMapper {
    @Autowired
    protected PersonDao personDao;

    @Mapping(target = "person", expression = "java(personDao.getById(personCredentialsDto.getPersonId()))")
    public abstract PersonCredentials mapToPersonCredentials(PersonCredentialsDto personCredentialsDto);

    @Mapping(target = "personId", expression = "java(personCredentials.getPerson().getId())")
    public abstract PersonCredentialsDto mapToPersonCredentialsDto(PersonCredentials personCredentials);
}
