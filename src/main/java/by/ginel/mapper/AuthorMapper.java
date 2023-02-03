package by.ginel.mapper;

import by.ginel.dto.AuthorDto;
import by.ginel.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author mapToAuthor(AuthorDto authorDto);

    AuthorDto mapToAuthorDto(Author author);
}
