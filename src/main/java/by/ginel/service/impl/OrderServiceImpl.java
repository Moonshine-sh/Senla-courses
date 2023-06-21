package by.ginel.service.impl;

import by.ginel.dao.BookItemDao;
import by.ginel.dao.OrderDao;
import by.ginel.dao.PersonDao;
import by.ginel.dao.StatusDao;
import by.ginel.dto.BookDto;
import by.ginel.dto.BookItemDto;
import by.ginel.dto.OrderDto;
import by.ginel.entity.Book;
import by.ginel.entity.BookItem;
import by.ginel.entity.MyUser;
import by.ginel.entity.Order;
import by.ginel.handler.exception.NoAccessToOrderException;
import by.ginel.handler.exception.OrderNotFoundException;
import by.ginel.mapper.BookMapper;
import by.ginel.mapper.OrderMapper;
import by.ginel.service.OrderService;
import by.ginel.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderDao orderDao;
    private final StatusDao statusDao;
    private final BookMapper bookMapper;
    private final BookItemDao bookItemDao;
    private final PersonDao personDao;

    @Override
    public List<OrderDto> getAll(Pageable pageable) {
        List<Order> orders = orderDao.getAll(pageable);
        return orders
                .stream()
                .map(orderMapper::mapToOderDto)
                .toList();
    }

    @Override
    public List<OrderDto> getAll() {
        return getAll(Pageable.maxPage());
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = orderDao.getById(id);
        return orderMapper.mapToOderDto(order);
    }

    @Override
    public OrderDto save(OrderDto entityDto) {
        Order order = orderDao.save(orderMapper.mapToOrder(entityDto));
        return orderMapper.mapToOderDto(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public OrderDto update(OrderDto entityDto) {
        Order order = orderDao.update(orderMapper.mapToOrder(entityDto));
        return orderMapper.mapToOderDto(order);
    }

    @Override
    public List<OrderDto> getAllByPersonId(Long id, Pageable pageable) {
        log.info("Executing method getAllByPersonId()");

        List<Order> orders = orderDao.getAll(pageable);
        return orders
                .stream()
                .filter(order -> order.getPerson().getId().equals(id))
                .map(orderMapper::mapToOderDto)
                .toList();
    }

    @Override
    public void updateStatus(OrderDto newOrder) {
        log.info("Executing method updateStatus()");

        Order order = orderDao.getById(newOrder.getId());
        order.setStatus(statusDao.findByName(newOrder.getStatus()));
    }

    @Override
    public void placeOrder(Long personId, List<Long> cart) {
        log.info("Executing method placeOrder()");

        Order order = getNewOrder(personId);

        Long price = cart.stream()
                         .map(bookId -> {
                             BookItem bookItem = bookItemDao.findFreeBookItemByBookId(bookId);
                             bookItem.setOrder(order);
                             bookItemDao.update(bookItem);
                             return bookItem;
                         })
                         .mapToLong(bookItem -> bookItem.getPrice().longValue())
                         .sum();

        order.setPrice(BigDecimal.valueOf(price));
        orderDao.update(order);
    }

    @Override
    public List<OrderDto> getAllByUser(MyUser user) {
        List<OrderDto> orders;
        if (user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            orders = getAll();
        } else {
            orders = getAllByPersonId(user.getId(), Pageable.maxPage());
        }
        return orders;
    }

    @Override
    public List<BookDto> getBooksFromOrder(Long id, List<OrderDto> orders) {
        orders.stream()
              .filter(item -> item.getId().equals(id))
              .findFirst().orElseThrow(() -> new NoAccessToOrderException("You dont have access to this order"));

        Order order = orderDao.getById(id);
        List<Book> books = order.getBookItems().stream().map(BookItem::getBook).toList();
        return books.stream()
                    .map(bookMapper::mapToBookDto)
                    .toList();
    }

    @Override
    public void removeOrder(Long id, List<OrderDto> orders) {
        Optional<OrderDto> orderDto = orders.stream()
                                            .filter(order -> order.getId().equals(id) && Objects.equals(order.getStatus(), "BOOKED"))
                                            .findFirst();

        if (orderDto.isPresent()) {
            delete(orderDto.get().getId());
        } else {
            throw new OrderNotFoundException("No order with such Id");
        }
    }

    private Order getNewOrder(Long id) {
        log.info("Executing method placeOrder()");

        Order order = Order.builder()
                           .person(personDao.getById(id))
                           .date(new Timestamp(new Date().getTime()))
                           .status(statusDao.findByName("BOOKED"))
                           .build();
        return orderDao.save(order);
    }
}
