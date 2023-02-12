package by.ginel.controller;

import by.ginel.dto.BookDto;
import by.ginel.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(bookService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(bookService.getById(id));
    }

    public void save(BookDto bookDto) throws JsonProcessingException, SQLException, InterruptedException {
        bookService.save(bookDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        bookService.delete(id);
    }

    public void update(BookDto bookDto) throws JsonProcessingException, SQLException, InterruptedException {
        bookService.update(bookDto);
    }
}
