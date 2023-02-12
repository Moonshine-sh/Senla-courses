package by.ginel;

import by.ginel.dao.GenreDao;
import by.ginel.entity.Genre;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "by.ginel")
public class Application {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        GenreDao genreDao = context.getBean(GenreDao.class);

        Genre genre = Genre.builder()
                .name("ISEKAI")
                .build();

        System.out.println("getAll() size - " + genreDao.getAll().size());
        System.out.println("save() assigned id - " + genreDao.save(genre).getId());
        System.out.println("getAll() size - " + genreDao.getAll().size());
    }
}
