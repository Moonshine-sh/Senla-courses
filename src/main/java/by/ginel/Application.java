package by.ginel;

import by.ginel.dao.PersonCredentialsDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "by.ginel")
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        PersonCredentialsDao personCredentialsDao = context.getBean(PersonCredentialsDao.class);

        System.out.println(personCredentialsDao.getEntityWithFetchCriteria(28L).getPerson().getId());

        System.out.println(personCredentialsDao.getEntityWithFetchJPQL(28L).getPerson().getFirstName());

        System.out.println(personCredentialsDao.getEntityWithNamedGraph(28L).getPerson().getLastName());

    }
}
