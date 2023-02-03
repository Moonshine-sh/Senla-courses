package by.ginel.controller;

import by.ginel.dto.BookItemDto;
import by.ginel.service.BookItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookItemController {
    private final BookItemService bookItemService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookItemService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookItemService.getById(id));
    }

    public String save(BookItemDto bookItemDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookItemService.save(bookItemDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookItemService.delete(id));
    }

    public String update(BookItemDto bookItemDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookItemService.update(bookItemDto));
    }
}
