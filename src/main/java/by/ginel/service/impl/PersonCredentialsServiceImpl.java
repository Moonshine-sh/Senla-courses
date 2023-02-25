package by.ginel.service.impl;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.dto.PersonCredentialsDto;
import by.ginel.mapper.PersonCredentialsMapper;
import by.ginel.service.PersonCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class PersonCredentialsServiceImpl implements PersonCredentialsService {
    private final PersonCredentialsMapper personCredentialsMapper;
    private final PersonCredentialsDao personCredentialsDao;

    @Override
    public List<PersonCredentialsDto> getAll() throws SQLException, InterruptedException {
        return personCredentialsDao.getAll().stream().map(personCredentialsMapper::mapToPersonCredentialsDto).toList();
    }

    @Override
    public PersonCredentialsDto getById(Long id) throws SQLException, InterruptedException {
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentialsDao.getById(id));
    }

    @Override
    public PersonCredentialsDto save(PersonCredentialsDto entityDto) throws SQLException, InterruptedException {
        personCredentialsDao.save(personCredentialsMapper.mapToPersonCredentials(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        personCredentialsDao.delete(id);
    }

    @Override
    public PersonCredentialsDto update(PersonCredentialsDto entityDto) throws SQLException, InterruptedException {
        personCredentialsDao.update(personCredentialsMapper.mapToPersonCredentials(entityDto));
        return null;
    }
}
