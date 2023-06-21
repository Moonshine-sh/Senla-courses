package by.ginel.service;

import by.ginel.dto.BookDto;
import by.ginel.dto.OrderDto;
import by.ginel.entity.MyUser;
import by.ginel.util.Pageable;

import java.util.List;

public interface OrderService extends Service<OrderDto> {

    List<OrderDto> getAllByPersonId(Long id, Pageable pageable);

    void updateStatus(OrderDto newOrder);

    void placeOrder(Long id, List<Long> cart);

    List<OrderDto> getAllByUser(MyUser user);

    List<BookDto> getBooksFromOrder(Long id, List<OrderDto> orders);

    void removeOrder(Long id, List<OrderDto> orders);
}
