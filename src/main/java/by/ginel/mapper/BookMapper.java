package by.ginel.mapper;

import by.ginel.dao.AuthorDao;
import by.ginel.dao.GenreDao;
import by.ginel.dto.BookDto;
import by.ginel.dto.GenreDto;
import by.ginel.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", imports = {Author.class, Genre.class})
public abstract class BookMapper {
    @Autowired
    protected GenreDao genreDao;
    @Autowired
    protected AuthorDao authorDao;

    @Mappings({
            @Mapping(target = "genres", expression = "java(bookDto.getGenres().stream().map(genreDao::findByName).toList())"),
            @Mapping(target = "authors", expression = "java(bookDto.getAuthors().stream().map(authorDao::findByName).toList())")
    })
    public abstract Book mapToBook(BookDto bookDto);

    @Mappings({
            @Mapping(target = "genres", expression = "java(book.getGenres().stream().map(Genre::getName).toList())"),
            @Mapping(target = "authors", expression = "java(book.getAuthors().stream().map(Author::getName).toList())")
    })
    public abstract BookDto mapToBookDto(Book book);
}
