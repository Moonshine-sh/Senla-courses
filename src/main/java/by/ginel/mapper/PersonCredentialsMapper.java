package by.ginel.mapper;

import by.ginel.dto.PersonCredentialsDto;
import by.ginel.entity.PersonCredentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonCredentialsMapper {
    PersonCredentials mapToPersonCredentials(PersonCredentialsDto personCredentialsDto);

    PersonCredentialsDto mapToPersonCredentialsDto(PersonCredentials personCredentials);
}
