package by.ginel.controller;

import by.ginel.dto.OrderDto;
import by.ginel.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping
    public OrderDto update(@RequestBody OrderDto orderDto) {
        return orderService.update(orderDto);
    }
}
