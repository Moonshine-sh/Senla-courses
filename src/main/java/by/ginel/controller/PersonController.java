package by.ginel.controller;

import by.ginel.dto.PersonDto;
import by.ginel.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(personService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personService.getById(id));
    }

    public String save(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personService.save(personDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personService.delete(id));
    }

    public String update(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personService.update(personDto));
    }
}
