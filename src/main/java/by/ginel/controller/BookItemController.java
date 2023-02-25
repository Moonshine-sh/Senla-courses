package by.ginel.controller;

import by.ginel.dto.BookItemDto;
import by.ginel.service.BookItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class BookItemController {
    private final BookItemService bookItemService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(bookItemService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(bookItemService.getById(id));
    }

    public void save(BookItemDto bookItemDto) throws JsonProcessingException, SQLException, InterruptedException {
        bookItemService.save(bookItemDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        bookItemService.delete(id);
    }

    public void update(BookItemDto bookItemDto) throws JsonProcessingException, SQLException, InterruptedException {
        bookItemService.update(bookItemDto);
    }
}
