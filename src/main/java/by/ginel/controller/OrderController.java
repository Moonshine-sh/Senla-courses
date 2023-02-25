package by.ginel.controller;

import by.ginel.dto.OrderDto;
import by.ginel.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(orderService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(orderService.getById(id));
    }

    public void save(OrderDto orderDto) throws JsonProcessingException, SQLException, InterruptedException {
        orderService.save(orderDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        orderService.delete(id);
    }

    public void update(OrderDto orderDto) throws JsonProcessingException, SQLException, InterruptedException {
        orderService.update(orderDto);
    }
}
