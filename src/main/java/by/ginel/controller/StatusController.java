package by.ginel.controller;

import by.ginel.dto.StatusDto;
import by.ginel.service.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(statusService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(statusService.getById(id));
    }

    public void save(StatusDto statusDto) throws JsonProcessingException, SQLException, InterruptedException {
        statusService.save(statusDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        statusService.delete(id);
    }

    public void update(StatusDto statusDto) throws JsonProcessingException, SQLException, InterruptedException {
        statusService.update(statusDto);
    }
}
