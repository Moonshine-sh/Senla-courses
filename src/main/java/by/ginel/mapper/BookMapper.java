package by.ginel.mapper;

import by.ginel.dto.BookDto;
import by.ginel.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book mapToBook(BookDto bookDto);

    BookDto mapToBookDto(Book book);
}
