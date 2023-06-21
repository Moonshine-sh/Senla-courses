package by.ginel.controller;

import by.ginel.dto.BookDto;
import by.ginel.dto.OrderDto;
import by.ginel.entity.MyUser;
import by.ginel.handler.exception.CartIsEmptyException;
import by.ginel.security.JwtService;
import by.ginel.service.OrderService;
import by.ginel.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(@RequestParam Pageable pageable) {
        List<OrderDto> orders = orderService.getAll(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<OrderDto>> getOrders(HttpServletRequest request,@RequestParam Pageable pageable){
        List<OrderDto> orders = orderService.getAllByPersonId(getUser(request).getId(), pageable);
        return ResponseEntity.ok(orders);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<List<BookDto>> getOrderDetails(@PathVariable Long id, HttpServletRequest request) {
        List<OrderDto> orders = orderService.getAllByUser(getUser(request));
        List<BookDto> books = orderService.getBooksFromOrder(id, orders);
        return ResponseEntity.ok(books);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody List<Long> cart, HttpServletRequest request) {
        if(CollectionUtils.isEmpty(cart)){
            throw new CartIsEmptyException("Trying to place order with empty cart object");
        }

        orderService.placeOrder(getUser(request).getId(), cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeOrder(@PathVariable Long id, HttpServletRequest request, @RequestParam Pageable pageable) {
        List<OrderDto> orders = orderService.getAllByPersonId(getUser(request).getId(), pageable);
        orderService.removeOrder(id, orders);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping
    public ResponseEntity<Void> updateOrder(@RequestBody OrderDto orderDto) {
        orderService.updateStatus(orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private MyUser getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        return (MyUser) userDetailsService.loadUserByUsername(username);
    }
}
