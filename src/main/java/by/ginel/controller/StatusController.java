package by.ginel.controller;

import by.ginel.dto.StatusDto;
import by.ginel.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statuses")
public class StatusController {
    private final StatusService statusService;

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping
    public List<StatusDto> getAll() {
        return statusService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/{id}")
    public StatusDto getById(@PathVariable Long id) {
        return statusService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping
    public StatusDto save(@RequestBody StatusDto statusDto) {
        return statusService.save(statusDto);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        statusService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping
    public StatusDto update(@RequestBody StatusDto statusDto) {
        return statusService.update(statusDto);
    }
}
