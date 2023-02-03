package by.ginel.controller;

import by.ginel.dto.GenreDto;
import by.ginel.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(genreService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(genreService.getById(id));
    }

    public void save(GenreDto genreDto) throws JsonProcessingException, SQLException, InterruptedException {
        genreService.save(genreDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        genreService.delete(id);
    }

    public void update(GenreDto genreDto) throws JsonProcessingException, SQLException, InterruptedException {
        genreService.update(genreDto);
    }
}
