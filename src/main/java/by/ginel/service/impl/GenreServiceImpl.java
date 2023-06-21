package by.ginel.service.impl;

import by.ginel.dao.GenreDao;
import by.ginel.dto.GenreDto;
import by.ginel.mapper.GenreMapper;
import by.ginel.service.GenreService;
import by.ginel.util.Pageable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GenreServiceImpl implements GenreService {
    private GenreMapper genreMapper;
    private GenreDao genreDao;

    @Override
    public List<GenreDto> getAll(Pageable pageable) {
        return genreDao.getAll(pageable).stream().map(genreMapper::mapToGenreDto).toList();
    }

    @Override
    public List<GenreDto> getAll() {
        return getAll(Pageable.maxPage());
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
    public void delete(Long id) {
        genreDao.delete(id);
    }

    @Override
    public GenreDto update(GenreDto entityDto) {
        return genreMapper.mapToGenreDto(genreDao.update(genreMapper.mapToGenre(entityDto)));
    }
}
