package by.ginel.controller;

import by.ginel.dto.PersonDto;
import by.ginel.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(personService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(personService.getById(id));
    }

    public Long save(PersonDto personDto) throws JsonProcessingException, SQLException, InterruptedException {
        return personService.save(personDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        personService.delete(id);
    }

    public void update(PersonDto personDto) throws JsonProcessingException, SQLException, InterruptedException {
        personService.update(personDto);
    }
}
