package by.ginel.controller;

import by.ginel.dto.AuthorDto;
import by.ginel.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(authorService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(authorService.getById(id));
    }

    public void save(AuthorDto authorDto) throws JsonProcessingException, SQLException, InterruptedException {
        authorService.save(authorDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        authorService.delete(id);
    }

    public void update(AuthorDto authorDto) throws JsonProcessingException, SQLException, InterruptedException {
        authorService.update(authorDto);
    }
}
