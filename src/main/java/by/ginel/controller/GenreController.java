package by.ginel.controller;

import by.ginel.dto.GenreDto;
import by.ginel.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.getById(id));
    }

    public String save(GenreDto genreDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.save(genreDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.delete(id));
    }

    public String update(GenreDto genreDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreService.update(genreDto));
    }
}
