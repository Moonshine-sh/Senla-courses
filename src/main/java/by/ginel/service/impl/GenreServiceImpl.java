package by.ginel.service.impl;

import by.ginel.dao.GenreDao;
import by.ginel.dto.GenreDto;
import by.ginel.mapper.GenreMapper;
import by.ginel.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreMapper genreMapper;
    private final GenreDao genreDao;

    @Override
    public List<GenreDto> getAll() {
        return genreDao.getAll().stream().map(genreMapper::mapToGenreDto).toList();
    }

    @Override
    public GenreDto getById(Long id) {
        return genreMapper.mapToGenreDto(genreDao.getById(id));
    }

    @Override
    public GenreDto save(GenreDto entityDto) {
        return genreMapper.mapToGenreDto(genreDao.save(genreMapper.mapToGenre(entityDto)));
    }

    @Override
    public Long delete(Long id) {
        return genreDao.delete(id);
    }

    @Override
    public GenreDto update(GenreDto entityDto) {
        return genreMapper.mapToGenreDto(genreDao.update(genreMapper.mapToGenre(entityDto)));
    }
}
