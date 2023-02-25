package by.ginel.controller;

import by.ginel.dto.AuthorDto;
import by.ginel.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public  String test(){
        return "hello";
    }

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
