package by.ginel.mapper;

import by.ginel.dao.BookDao;
import by.ginel.dao.OrderDao;
import by.ginel.dto.BookItemDto;
import by.ginel.entity.BookItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BookItemMapper {
    @Autowired
    protected BookDao bookDao;
    @Autowired
    protected OrderDao orderDao;

    @Mappings({
            @Mapping(target = "book", expression = "java(bookDao.getById(bookItemDto.getBookId()))"),
            @Mapping(target = "order", expression = "java(orderDao.getById(bookItemDto.getOrderId()))")
    })
    public  abstract BookItem mapToBookItem(BookItemDto bookItemDto);

    @Mappings({
            @Mapping(target = "bookId", expression = "java(bookItem.getBook().getId())"),
            @Mapping(target = "orderId", expression = "java(bookItem.getOrder().getId())")
    })
    public  abstract BookItemDto mapToBookItemDto(BookItem bookItem);
}
