package by.ginel.service.impl;

import by.ginel.dao.PersonDao;
import by.ginel.dto.PersonDto;
import by.ginel.mapper.PersonMapper;
import by.ginel.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonMapper personMapper;
    private final PersonDao personDao;

    @Override
    public List<PersonDto> getAll()  {
        return personDao.getAll().stream().map(personMapper::mapToPersonDto).toList();
    }

    @Override
    public PersonDto getById(Long id)  {
        return personMapper.mapToPersonDto(personDao.getById(id));
    }

    @Override
    public PersonDto save(PersonDto entityDto) {
        return personMapper.mapToPersonDto(personDao.save(personMapper.mapToPerson(entityDto)));
    }

    @Override
    public void delete(Long id)  {
        personDao.delete(id);
    }

    @Override
    public PersonDto update(PersonDto entityDto)  {
        return personMapper.mapToPersonDto(personDao.update(personMapper.mapToPerson(entityDto)));
    }
}
