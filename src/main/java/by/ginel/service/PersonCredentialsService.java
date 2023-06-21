package by.ginel.service;

import by.ginel.dto.PersonCredentialsDto;
import by.ginel.dto.PersonDto;
import by.ginel.dto.RegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public interface PersonCredentialsService extends Service<PersonCredentialsDto> {
    PersonCredentialsDto findByLogin(String login);

    PersonDto isUserValid(RegisterDto registerDto);
}
