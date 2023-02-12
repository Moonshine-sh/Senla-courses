package by.ginel.service.impl;

import by.ginel.dao.PersonDao;
import by.ginel.dto.PersonDto;
import by.ginel.mapper.PersonMapper;
import by.ginel.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonMapper personMapper;
    private final PersonDao personDao;

    @Override
    public List<PersonDto> getAll() throws SQLException, InterruptedException {
        return personDao.getAll().stream().map(personMapper::mapToPersonDto).toList();
    }

    @Override
    public PersonDto getById(Long id) throws SQLException, InterruptedException {
        return personMapper.mapToPersonDto(personDao.getById(id));
    }

    @Override
    public Long save(PersonDto entityDto) throws SQLException, InterruptedException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        personDao.delete(id);
    }

    @Override
    public void update(PersonDto entityDto) throws SQLException, InterruptedException {
        personDao.update(personMapper.mapToPerson(entityDto));
    }
}
