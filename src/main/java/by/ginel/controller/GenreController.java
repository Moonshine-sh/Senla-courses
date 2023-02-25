package by.ginel.controller;

import by.ginel.dto.GenreDto;
import by.ginel.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public List<GenreDto> getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public GenreDto getById(@PathVariable Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return genreService.getById(id);
    }

    @PostMapping("/save")
    public GenreDto save(@RequestBody GenreDto genreDto) throws JsonProcessingException, SQLException, InterruptedException {
        return genreService.save(genreDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws JsonProcessingException, SQLException, InterruptedException {
        genreService.delete(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody GenreDto genreDto) throws JsonProcessingException, SQLException, InterruptedException {
        genreService.update(genreDto);
    }
}
