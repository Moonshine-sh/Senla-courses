package by.ginel.controller;

import by.ginel.dto.RoleDto;
import by.ginel.service.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(roleService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(roleService.getById(id));
    }

    public String save(RoleDto roleDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(roleService.save(roleDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(roleService.delete(id));
    }

    public String update(RoleDto roleDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(roleService.update(roleDto));
    }
}
