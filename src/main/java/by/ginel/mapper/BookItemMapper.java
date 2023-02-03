package by.ginel.mapper;

import by.ginel.dto.BookItemDto;
import by.ginel.entity.BookItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookItemMapper {
    BookItem mapToBookItem(BookItemDto bookItemDto);

    BookItemDto mapToBookItemDto(BookItem bookItem);
}
