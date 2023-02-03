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
public interface PersonCredentialsMapper {
    PersonCredentials mapToPersonCredentials(PersonCredentialsDto personCredentialsDto);

    PersonCredentialsDto mapToPersonCredentialsDto(PersonCredentials personCredentials);
}
