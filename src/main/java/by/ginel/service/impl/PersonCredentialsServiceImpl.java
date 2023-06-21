package by.ginel.service.impl;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.dao.PersonDao;
import by.ginel.dao.RoleDao;
import by.ginel.dto.PersonCredentialsDto;
import by.ginel.dto.PersonDto;
import by.ginel.dto.RegisterDto;
import by.ginel.entity.Person;
import by.ginel.entity.PersonCredentials;
import by.ginel.handler.exception.UsernameTakenException;
import by.ginel.mapper.PersonCredentialsMapper;
import by.ginel.mapper.PersonMapper;
import by.ginel.service.PersonCredentialsService;
import by.ginel.service.PersonService;
import by.ginel.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PersonCredentialsServiceImpl implements PersonCredentialsService {
    private final PersonCredentialsMapper personCredentialsMapper;
    private final PersonCredentialsDao personCredentialsDao;
    private final RoleDao roleDao;
    private final PersonService personService;
    private final PersonDao personDao;
    private final PersonMapper personMapper;

    @Override
    public List<PersonCredentialsDto> getAll(Pageable pageable) {
        List<PersonCredentials> personCredentials = personCredentialsDao.getAll(pageable);
        return personCredentials
                .stream()
                .map(personCredentialsMapper::mapToPersonCredentialsDto)
                .toList();
    }

    @Override
    public List<PersonCredentialsDto> getAll() {
        return getAll(Pageable.maxPage());
    }

    @Override
    public PersonCredentialsDto getById(Long id) {
        PersonCredentials personCredentials = personCredentialsDao.getById(id);
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentials);
    }

    @Override
    public PersonCredentialsDto save(PersonCredentialsDto entityDto) {
        PersonCredentials personCredentials = personCredentialsDao.save(personCredentialsMapper.mapToPersonCredentials(entityDto));
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentials);
    }

    @Override
    public void delete(Long id) {
        personCredentialsDao.delete(id);
    }

    @Override
    public PersonCredentialsDto update(PersonCredentialsDto entityDto) {
        PersonCredentials personCredentials = personCredentialsDao.update(personCredentialsMapper.mapToPersonCredentials(entityDto));
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentials);
    }

    @Override
    public PersonCredentialsDto findByLogin(String login) {
        log.info("Executing method findByLogin()");

        Optional<PersonCredentials> personCredentials = personCredentialsDao.findByUsername(login);
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentials.get());
    }

    @Override
    public PersonDto isUserValid(RegisterDto registerDto) {
        log.info("Executing method isUserValid()");

        Optional<PersonCredentials> personCredentialsFromDB = personCredentialsDao.findByUsername(registerDto.getUsername());
        if(personCredentialsFromDB.isEmpty()) {
            Person person = Person.builder()
                                .firstName(registerDto.getFirstName())
                                .lastName(registerDto.getLastName())
                                .email(registerDto.getEmail())
                                .mobNum(registerDto.getMobNum())
                                .locked(false)
                                .enabled(false)
                                .roles(List.of(roleDao.findByName("user")))
                                .build();

            Person savedPerson = personDao.save(person);
            PersonCredentials personCredentials = PersonCredentials.builder()
                                                       .login(registerDto.getUsername())
                                                       .password(registerDto.getPassword())
                                                       .person(savedPerson)
                                                       .build();
            personCredentialsDao.save(personCredentials);
            return personMapper.mapToPersonDto(savedPerson);
        } else {
            throw new UsernameTakenException("Username already taken");
        }
    }
}
