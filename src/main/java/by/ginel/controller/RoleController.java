package by.ginel.controller;

import by.ginel.dto.RoleDto;
import by.ginel.service.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(roleService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(roleService.getById(id));
    }

    public void save(RoleDto roleDto) throws JsonProcessingException, SQLException, InterruptedException {
        roleService.save(roleDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        roleService.delete(id);
    }

    public void update(RoleDto roleDto) throws JsonProcessingException, SQLException, InterruptedException {
        roleService.update(roleDto);
    }
}
