package by.ginel.service;


import by.ginel.config.ServiceConfig;
import by.ginel.dao.GenreDao;
import by.ginel.dao.impl.GenreDaoImpl;
import by.ginel.dto.GenreDto;
import by.ginel.entity.Genre;
import by.ginel.mapper.GenreMapper;
import by.ginel.mapper.GenreMapperImpl;
import by.ginel.service.impl.GenreServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.sql.SQLException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class}, loader = AnnotationConfigContextLoader.class)
public class GenreServiceTest {
    @InjectMocks
    private final GenreMapper genreMapper = new GenreMapperImpl();

    @Mock
    private final GenreDao genreDao = Mockito.mock(GenreDaoImpl.class);

    @InjectMocks
    private final GenreService genreService = new GenreServiceImpl(genreMapper, genreDao);

    @Test
    public void getByIdTest() throws SQLException, InterruptedException {
        Genre genre = Genre.builder()
                .id(1L)
                .name("ACTION")
                .build();

        Mockito.when(genreDao.getById(genre.getId())).thenReturn(genre);

        Assert.assertEquals(genreService.getById(genre.getId()).getName(), genre.getName());
    }

    @Test
    public void getAllTest() throws SQLException, InterruptedException {
        Genre genre1 = Genre.builder()
                .id(1L)
                .name("ACTION")
                .build();
        Genre genre2 = Genre.builder()
                .id(21L)
                .name("ACTION")
                .build();
        Genre genre3 = Genre.builder()
                .id(3L)
                .name("ACTION")
                .build();

        List<Genre> genreList = List.of(genre1, genre2, genre3);

        Mockito.when(genreDao.getAll()).thenReturn(genreList);

        Assert.assertEquals(genreService.getAll().size(), genreList.size());
    }

    @Test
    public void saveTest() throws SQLException, InterruptedException {
        GenreDto genreDto = GenreDto.builder()
                .name("ACTION")
                .build();
        Genre genre = Genre.builder()
                .id(1L)
                .name("ACTION")
                .build();

        Mockito.when(genreDao.save(genreMapper.mapToGenre(genreDto))).thenReturn(genre);

        Assert.assertEquals(genreService.save(genreDto).getId(), genre.getId());
    }

    @Test
    public void updateTest() throws SQLException, InterruptedException {
        GenreDto genreDto = GenreDto.builder()
                .id(1L)
                .name("ACTION")
                .build();
        Genre genre = Genre.builder()
                .id(1L)
                .name("DETECTIVE")
                .build();

        Mockito.when(genreDao.update(genreMapper.mapToGenre(genreDto))).thenReturn(genre);

        Assert.assertEquals(genreService.update(genreDto).getName(), genre.getName());
    }

    @Test
    public void deleteTest() throws SQLException, InterruptedException {
        genreService.delete(1L);

        Mockito.verify(genreDao, Mockito.times(1)).delete(1L);
    }
}
