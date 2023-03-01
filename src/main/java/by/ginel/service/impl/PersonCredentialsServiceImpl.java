package by.ginel.service.impl;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.dto.PersonCredentialsDto;
import by.ginel.mapper.PersonCredentialsMapper;
import by.ginel.service.PersonCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class PersonCredentialsServiceImpl implements PersonCredentialsService {
    private final PersonCredentialsMapper personCredentialsMapper;
    private final PersonCredentialsDao personCredentialsDao;

    @Override
    public List<PersonCredentialsDto> getAll() {
        return personCredentialsDao.getAll().stream().map(personCredentialsMapper::mapToPersonCredentialsDto).toList();
    }

    @Override
    public PersonCredentialsDto getById(Long id) {
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentialsDao.getById(id));
    }

    @Override
    public PersonCredentialsDto save(PersonCredentialsDto entityDto) {
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentialsDao.save(personCredentialsMapper.mapToPersonCredentials(entityDto)));
    }

    @Override
    public void delete(Long id) {
        personCredentialsDao.delete(id);
    }

    @Override
    public PersonCredentialsDto update(PersonCredentialsDto entityDto) {
        return personCredentialsMapper.mapToPersonCredentialsDto(personCredentialsDao.update(personCredentialsMapper.mapToPersonCredentials(entityDto)));
    }
}
