package by.ginel.service.impl;

import by.ginel.aspect.Transaction;
import by.ginel.dao.PersonCredentialsDao;
import by.ginel.dto.PersonCredentialsDto;
import by.ginel.mapper.PersonCredentialsMapper;
import by.ginel.service.PersonCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
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

    @Transaction
    @Override
    public Long save(PersonCredentialsDto entityDto) throws SQLException, InterruptedException {
        personCredentialsDao.save(personCredentialsMapper.mapToPersonCredentials(entityDto));
        return null;
    }
    @Transaction
    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        personCredentialsDao.delete(id);
    }

    @Transaction
    @Override
    public void update(PersonCredentialsDto entityDto) throws SQLException, InterruptedException {
        personCredentialsDao.update(personCredentialsMapper.mapToPersonCredentials(entityDto));
    }
}
