package by.ginel;

import by.ginel.config.AppConfig;
import by.ginel.config.DatabaseConfig;
import by.ginel.controller.*;
import by.ginel.dto.*;
import by.ginel.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, DatabaseConfig.class);
        PersonController personController = context.getBean(PersonController.class);
        PersonCredentialsController personCredentialsController = context.getBean(PersonCredentialsController.class);

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                try {
                    test(personController, personCredentialsController);
                } catch (JsonProcessingException | SQLException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread.start();
        }
    }

    private static void test(PersonController personController, PersonCredentialsController personCredentialsController) throws JsonProcessingException, SQLException, InterruptedException {
        PersonDto person = PersonDto.builder()
                .firstName("Vlad")
                .lastName("Lisai")
                .email("vlad@gmail.com")
                .mobNum("+123456789")
                .locked(Boolean.FALSE)
                .enabled(Boolean.TRUE)
                .roles(List.of(1L, 2L))
                .build();
        Long id = personController.save(person);

        PersonCredentialsDto personCredentialsDto = PersonCredentialsDto.builder()
                .login("qwerty")
                .personId(id)
                .password("ytrewq")
                .build();
        personCredentialsController.save(personCredentialsDto);

        System.out.println(personController.getById(id));

        System.out.println(personController.getAll());
    }
}
