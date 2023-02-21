package by.ginel.service.impl;

import by.ginel.dao.AuthorDao;
import by.ginel.dto.AuthorDto;
import by.ginel.mapper.AuthorMapper;
import by.ginel.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorDao authorDao;

    @Override
    public List<AuthorDto> getAll() throws SQLException, InterruptedException {
        return authorDao.getAll().stream().map(authorMapper::mapToAuthorDto).toList();
    }

    @Override
    public AuthorDto getById(Long id) throws SQLException, InterruptedException {
        return authorMapper.mapToAuthorDto(authorDao.getById(id));
    }

    @Override
    public Long save(AuthorDto entityDto) throws SQLException, InterruptedException {
        authorDao.save(authorMapper.mapToAuthor(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        authorDao.delete(id);
    }

    @Override
    public void update(AuthorDto entityDto) throws SQLException, InterruptedException {
        authorDao.update(authorMapper.mapToAuthor(entityDto));
    }
}
