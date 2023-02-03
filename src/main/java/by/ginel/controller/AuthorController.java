package by.ginel.controller;

import by.ginel.dto.AuthorDto;
import by.ginel.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.getById(id));
    }

    public String save(AuthorDto authorDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.save(authorDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.delete(id));
    }

    public String update(AuthorDto authorDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorService.update(authorDto));
    }
}
