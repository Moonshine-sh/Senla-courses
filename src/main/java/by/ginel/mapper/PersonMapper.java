package by.ginel.mapper;

import by.ginel.dto.PersonDto;
import by.ginel.entity.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person mapToPerson(PersonDto personDto);

    PersonDto mapToPersonDto(Person person);
}
