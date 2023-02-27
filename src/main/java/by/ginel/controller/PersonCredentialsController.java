package by.ginel.controller;

import by.ginel.dto.PersonCredentialsDto;
import by.ginel.service.PersonCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people-credentials")
public class PersonCredentialsController {
    private final PersonCredentialsService personCredentialsService;

    @GetMapping
    public List<PersonCredentialsDto> getAll() {
        return personCredentialsService.getAll();
    }

    @GetMapping("/{id}")
    public PersonCredentialsDto getById(@PathVariable Long id) {
        return personCredentialsService.getById(id);
    }

    @PostMapping
    public PersonCredentialsDto save(@RequestBody PersonCredentialsDto personCredentialsDto) {
        return personCredentialsService.save(personCredentialsDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personCredentialsService.delete(id);
    }

    @PutMapping
    public PersonCredentialsDto update(@RequestBody PersonCredentialsDto personCredentialsDto) {
        return personCredentialsService.update(personCredentialsDto);
    }
}
