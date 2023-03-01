package by.ginel.controller;

import by.ginel.dto.PersonDto;
import by.ginel.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDto getById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @PostMapping
    public PersonDto save(@RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    @PutMapping
    public PersonDto update(@RequestBody PersonDto personDto) {
        return personService.update(personDto);
    }
}
