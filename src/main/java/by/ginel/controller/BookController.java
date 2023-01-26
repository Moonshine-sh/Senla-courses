package by.ginel.controller;

import by.ginel.dto.BookDto;
import by.ginel.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.getById(id));
    }

    public String save(BookDto bookDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.save(bookDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.delete(id));
    }

    public String update(BookDto bookDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookService.update(bookDto));
    }
}
