package by.ginel.controller;

import by.ginel.dto.StatusDto;
import by.ginel.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statuses")
public class StatusController {
    private final StatusService statusService;

    @GetMapping
    public List<StatusDto> getAll() {
        return statusService.getAll();
    }

    @GetMapping("/{id}")
    public StatusDto getById(@PathVariable Long id) {
        return statusService.getById(id);
    }

    @PostMapping
    public StatusDto save(@RequestBody StatusDto statusDto) {
        return statusService.save(statusDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        statusService.delete(id);
    }

    @PutMapping
    public StatusDto update(@RequestBody StatusDto statusDto) {
        return statusService.update(statusDto);
    }
}
