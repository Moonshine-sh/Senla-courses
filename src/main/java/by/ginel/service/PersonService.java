package by.ginel.service;

import by.ginel.dto.PersonDto;
import by.ginel.entity.VerificationToken;

import java.util.List;

public interface PersonService extends Service<PersonDto> {

    List<PersonDto> findAllByName(String name);

    boolean isAdmin(Long id);

    PersonDto getUserByToken(String token);

    VerificationToken getVerificationToken(String token);

    void createVerificationToken(PersonDto personGetDto, String token);

    void updateLocked(Long id);

    void activateUser(Long id);
}
