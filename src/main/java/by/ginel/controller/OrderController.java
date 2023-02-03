package by.ginel.controller;

import by.ginel.dto.OrderDto;
import by.ginel.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderService.getById(id));
    }

    public String save(OrderDto orderDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderService.save(orderDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderService.delete(id));
    }

    public String update(OrderDto orderDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderService.update(orderDto));
    }
}
