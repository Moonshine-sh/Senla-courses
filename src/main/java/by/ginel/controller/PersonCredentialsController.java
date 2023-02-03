package by.ginel.controller;

import by.ginel.dto.PersonCredentialsDto;
import by.ginel.service.PersonCredentialsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonCredentialsController {
    private final PersonCredentialsService personCredentialsService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(personCredentialsService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personCredentialsService.getById(id));
    }

    public String save(PersonCredentialsDto personCredentialsDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personCredentialsService.save(personCredentialsDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personCredentialsService.delete(id));
    }

    public String update(PersonCredentialsDto personCredentialsDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personCredentialsService.update(personCredentialsDto));
    }
}
