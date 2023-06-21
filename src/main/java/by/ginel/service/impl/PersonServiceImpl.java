package by.ginel.service.impl;

import by.ginel.dao.PersonDao;
import by.ginel.dao.RoleDao;
import by.ginel.dao.VerificationTokenDao;
import by.ginel.dto.PersonDto;
import by.ginel.entity.Person;
import by.ginel.entity.Role;
import by.ginel.entity.VerificationToken;
import by.ginel.mapper.PersonMapper;
import by.ginel.service.PersonService;
import by.ginel.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonMapper personMapper;
    private final PersonDao personDao;
    private final RoleDao roleDao;
    private final VerificationTokenDao verificationTokenDao;

    @Override
    public List<PersonDto> getAll(Pageable pageable) {
        List<Person> people = personDao.getAll(pageable);
        return people
                .stream()
                .map(personMapper::mapToPersonDto)
                .toList();
    }

    @Override
    public List<PersonDto> getAll()  {
        return getAll(Pageable.maxPage());
    }

    @Override
    public PersonDto getById(Long id)  {
        Person person = personDao.getById(id);
        return personMapper.mapToPersonDto(person);
    }

    @Override
    public PersonDto save(PersonDto entityDto) {
        Person person = personDao.save(personMapper.mapToPerson(entityDto));
        return personMapper.mapToPersonDto(person);
    }

    @Override
    public void delete(Long id)  {
        personDao.delete(id);
    }

    @Override
    public PersonDto update(PersonDto entityDto)  {
        Person person = personDao.update(personMapper.mapToPerson(entityDto));
        return personMapper.mapToPersonDto(person);
    }

    @Override
    public List<PersonDto> findAllByName(String name) {
        log.info("Executing method findAllByName()");

        List<Person> people = personDao.findAllByName(name);
        return people
                .stream()
                .map(personMapper::mapToPersonDto)
                .toList();
    }

    @Override
    public boolean isAdmin(Long id) {
        Person person = personDao.getById(id);
        return person.getRoles().contains(roleDao.findByName("admin"));
    }

    @Override
    public PersonDto getUserByToken(String token) {
        Person person = verificationTokenDao.findByToken(token).getPerson();
        return personMapper.mapToPersonDto(person);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenDao.findByToken(token);
    }

    @Override
    public void createVerificationToken(PersonDto personGetDto, String token) {
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .person(personDao.getById(personGetDto.getId()))
                .build();
        verificationTokenDao.save(verificationToken);
    }

    @Override
    public void updateLocked(Long id) {
        log.info("Executing method updateLocked()");

        Person person = personDao.getById(id);
        person.setLocked(!person.getLocked());
    }

    @Override
    public void activateUser(Long id) {
        log.info("Executing method updateEnable()");

        Person person = personDao.getById(id);
        person.setEnabled(true);
    }
}
