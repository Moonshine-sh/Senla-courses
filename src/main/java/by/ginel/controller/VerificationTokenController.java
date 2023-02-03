package by.ginel.controller;

import by.ginel.dto.VerificationTokenDto;
import by.ginel.service.VerificationTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationTokenController {
    private final VerificationTokenService verificationTokenService;
    private final ObjectMapper objectMapper;

    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(verificationTokenService.getAll());
    }

    public String getById(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(verificationTokenService.getById(id));
    }

    public String save(VerificationTokenDto verificationTokenDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(verificationTokenService.save(verificationTokenDto));
    }

    public String delete(Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(verificationTokenService.delete(id));
    }

    public String update(VerificationTokenDto verificationTokenDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(verificationTokenService.update(verificationTokenDto));
    }
}
