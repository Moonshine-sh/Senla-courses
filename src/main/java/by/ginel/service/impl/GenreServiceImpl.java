package by.ginel.service.impl;

import by.ginel.dao.GenreDao;
import by.ginel.dto.GenreDto;
import by.ginel.mapper.GenreMapper;
import by.ginel.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GenreServiceImpl implements GenreService {
    private GenreMapper genreMapper;
    private GenreDao genreDao;

    @Override
    public List<GenreDto> getAll() throws SQLException, InterruptedException {
        return genreDao.getAll().stream().map(genreMapper::mapToGenreDto).toList();
    }

    @Override
    public GenreDto getById(Long id) throws SQLException, InterruptedException {
        return genreMapper.mapToGenreDto(genreDao.getById(id));
    }

    @Override
    public GenreDto save(GenreDto entityDto) throws SQLException, InterruptedException {
        return genreMapper.mapToGenreDto(genreDao.save(genreMapper.mapToGenre(entityDto)));
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        genreDao.delete(id);
    }

    @Override
    public GenreDto update(GenreDto entityDto) throws SQLException, InterruptedException {
        return genreMapper.mapToGenreDto(genreDao.update(genreMapper.mapToGenre(entityDto)));
    }
}
