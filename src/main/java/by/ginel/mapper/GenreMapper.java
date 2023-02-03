package by.ginel.mapper;

import by.ginel.dto.GenreDto;
import by.ginel.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre mapToGenre(GenreDto genreDto);

    GenreDto mapToGenreDto(Genre genre);
}
