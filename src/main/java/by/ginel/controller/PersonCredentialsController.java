package by.ginel.controller;

import by.ginel.dto.PersonCredentialsDto;
import by.ginel.service.PersonCredentialsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class PersonCredentialsController {
    private final PersonCredentialsService personCredentialsService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(personCredentialsService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(personCredentialsService.getById(id));
    }

    public void save(PersonCredentialsDto personCredentialsDto) throws JsonProcessingException, SQLException, InterruptedException {
        personCredentialsService.save(personCredentialsDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        personCredentialsService.delete(id);
    }

    public void update(PersonCredentialsDto personCredentialsDto) throws JsonProcessingException, SQLException, InterruptedException {
        personCredentialsService.update(personCredentialsDto);
    }
}
