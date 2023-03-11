package by.ginel.controller;

import by.ginel.dto.VerificationTokenDto;
import by.ginel.service.VerificationTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tokens")
@PreAuthorize("hasAnyAuthority('admin')")
public class VerificationTokenController {
    private final VerificationTokenService verificationTokenService;

    @GetMapping
    public List<VerificationTokenDto> getAll() {
        return verificationTokenService.getAll();
    }

    @GetMapping("/{id}")
    public VerificationTokenDto getById(@PathVariable Long id) {
        return verificationTokenService.getById(id);
    }

    @PostMapping
    public VerificationTokenDto save(@RequestBody VerificationTokenDto verificationTokenDto) {
        return verificationTokenService.save(verificationTokenDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        verificationTokenService.delete(id);
    }

    @PutMapping
    public VerificationTokenDto update(@RequestBody VerificationTokenDto verificationTokenDto) {
        return verificationTokenService.update(verificationTokenDto);
    }
}
