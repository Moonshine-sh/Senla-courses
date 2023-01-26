package by.ginel.controller;

import by.ginel.dto.StatusDto;
import by.ginel.service.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(statusService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(statusService.getById(id));
    }

    public String save(StatusDto statusDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(statusService.save(statusDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(statusService.delete(id));
    }

    public String update(StatusDto statusDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(statusService.update(statusDto));
    }
}
