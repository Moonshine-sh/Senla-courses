package by.ginel.dao;

import by.ginel.config.DatabaseConfig;
import by.ginel.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class GenreDaoTest {
    @Autowired
    private GenreDao genreDao;

    @Test
    public void saveTest() throws SQLException, InterruptedException {
        Genre genre = Genre.builder()
                .name("ACTION")
                .build();

        Genre saved = genreDao.save(genre);

        Assert.assertEquals(1, (long) saved.getId());
    }

    @Test
    public void updateTest() throws SQLException, InterruptedException {
        Genre genre = Genre.builder()
                .name("ACTION")
                .build();

        Genre saved = genreDao.save(genre);

        saved.setName("FANTASY");

        genreDao.update(saved);

        Genre genreDB = genreDao.getById(saved.getId());
        Assert.assertEquals("FANTASY", genreDB.getName());
    }

    @Test
    public void deleteTest() throws SQLException, InterruptedException {
        Genre genre = Genre.builder()
                .name("ACTION")
                .build();

        Genre saved = genreDao.save(genre);

        genreDao.delete(saved.getId());

        Assert.assertEquals(0, genreDao.getAll().size());
    }

    @Test
    public void getAllTest() throws SQLException, InterruptedException {
        Genre genre1 = Genre.builder()
                .name("ACTION")
                .build();
        Genre genre2 = Genre.builder()
                .name("FANTASY")
                .build();
        Genre genre3 = Genre.builder()
                .name("DETECTIVE")
                .build();

        genreDao.save(genre1);
        genreDao.save(genre2);
        genreDao.save(genre3);

        Assert.assertEquals(3, genreDao.getAll().size());
    }

    @Test
    public void getByIdTest() throws SQLException, InterruptedException {
        Genre genre = Genre.builder()
                .name("ACTION")
                .build();

        Genre saved = genreDao.save(genre);

        Assert.assertEquals(saved, genreDao.getById(saved.getId()));
    }
}
