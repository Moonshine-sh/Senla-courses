package by.ginel.controller;

import by.ginel.dto.GenreDto;
import by.ginel.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping
    public List<GenreDto> getAll() {
        return genreService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/{id}")
    public GenreDto getById(@PathVariable Long id) {
        return genreService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping
    public GenreDto save(@RequestBody GenreDto genreDto) {
        return genreService.save(genreDto);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping
    public void update(@RequestBody GenreDto genreDto) {
        genreService.update(genreDto);
    }
}
