package by.ginel.service.impl;

import by.ginel.dao.AuthorDao;
import by.ginel.dto.AuthorDto;
import by.ginel.mapper.AuthorMapper;
import by.ginel.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorDao authorDao;

    @Override
    public List<AuthorDto> getAll() {
        return authorDao.getAll().stream().map(authorMapper::mapToAuthorDto).toList();
    }

    @Override
    public AuthorDto getById(Long id) {
        return authorMapper.mapToAuthorDto(authorDao.getById(id));
    }

    @Override
    public AuthorDto save(AuthorDto entityDto) {
        return authorMapper.mapToAuthorDto(authorDao.save(authorMapper.mapToAuthor(entityDto)));
    }

    @Override
    public Long delete(Long id) {
        return authorDao.delete(id);
    }

    @Override
    public AuthorDto update(AuthorDto entityDto) {
        return authorMapper.mapToAuthorDto(authorDao.update(authorMapper.mapToAuthor(entityDto)));
    }
}
