package by.ginel.service.impl;

import by.ginel.dao.GenreDao;
import by.ginel.dto.GenreDto;
import by.ginel.mapper.GenreMapper;
import by.ginel.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreMapper genreMapper;
    private final GenreDao genreDao;

    @Override
    public List<GenreDto> getAll() throws SQLException, InterruptedException {
        return genreDao.getAll().stream().map(genreMapper::mapToGenreDto).toList();
    }

    @Override
    public GenreDto getById(Long id) throws SQLException, InterruptedException {
        return genreMapper.mapToGenreDto(genreDao.getById(id));
    }

    @Override
    public Long save(GenreDto entityDto) throws SQLException, InterruptedException {
        genreDao.save(genreMapper.mapToGenre(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        genreDao.delete(id);
    }

    @Override
    public void update(GenreDto entityDto) throws SQLException, InterruptedException {
        genreDao.update(genreMapper.mapToGenre(entityDto));
    }
}
