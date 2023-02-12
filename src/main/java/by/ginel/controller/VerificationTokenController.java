package by.ginel.controller;

import by.ginel.dto.VerificationTokenDto;
import by.ginel.service.VerificationTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class VerificationTokenController {
    private final VerificationTokenService verificationTokenService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(verificationTokenService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        return objectMapper.writeValueAsString(verificationTokenService.getById(id));
    }

    public void save(VerificationTokenDto verificationTokenDto) throws JsonProcessingException, SQLException, InterruptedException {
        verificationTokenService.save(verificationTokenDto);
    }

    public void delete(Long id) throws JsonProcessingException, SQLException, InterruptedException {
        verificationTokenService.delete(id);
    }

    public void update(VerificationTokenDto verificationTokenDto) throws JsonProcessingException, SQLException, InterruptedException {
        verificationTokenService.update(verificationTokenDto);
    }
}
