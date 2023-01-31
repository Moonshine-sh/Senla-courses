package by.ginel;

import by.ginel.controller.*;
import by.ginel.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ComponentScan(basePackages = "by.ginel")
public class Application {
    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        authorTest(context.getBean(AuthorController.class));

        bookTest(context.getBean(BookController.class));

        bookItemTest(context.getBean(BookItemController.class));

        genreTest(context.getBean(GenreController.class));

        orderTest(context.getBean(OrderController.class));

        personCredentialsTest(context.getBean(PersonCredentialsController.class));

        personTest(context.getBean(PersonController.class));

        roleTest(context.getBean(RoleController.class));

        statusTest(context.getBean(StatusController.class));

        verificationTokenTest(context.getBean(VerificationTokenController.class));
    }

    private static void verificationTokenTest(VerificationTokenController verificationTokenController) throws JsonProcessingException {
        System.out.println("verificationTokenTest start");
        VerificationTokenDto verificationTokenDto1 = VerificationTokenDto.builder()
                .token("1hgfixgboieb")
                .expiryDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        VerificationTokenDto verificationTokenDto2 = VerificationTokenDto.builder()
                .token("er7vvh53c53h")
                .expiryDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        System.out.println(verificationTokenController.save(verificationTokenDto1));
        System.out.println(verificationTokenController.save(verificationTokenDto2));
        System.out.println(verificationTokenController.getById(0L));
        System.out.println(verificationTokenController.delete(1L));

        verificationTokenDto1.setId(0L);
        verificationTokenDto2.setToken("1wedfvbnji");
        System.out.println(verificationTokenController.update(verificationTokenDto1));
        System.out.println(verificationTokenController.getById(0L));
        System.out.println("verificationTokenTest end");
    }

    private static void statusTest(StatusController statusController) throws JsonProcessingException {
        System.out.println("statusTest start");
        StatusDto statusDto1 = StatusDto.builder()
                .name("Booked")
                .build();
        StatusDto statusDto2 = StatusDto.builder()
                .name("Taken")
                .build();

        System.out.println(statusController.save(statusDto1));
        System.out.println(statusController.save(statusDto2));
        System.out.println(statusController.getById(0L));
        System.out.println(statusController.delete(1L));

        statusDto1.setId(0L);
        statusDto1.setName("Returned");
        System.out.println(statusController.update(statusDto1));
        System.out.println(statusController.getById(0L));
        System.out.println("statusTest end");
    }

    private static void roleTest(RoleController roleController) throws JsonProcessingException {
        System.out.println("roleTest start");
        RoleDto roleDto1 = RoleDto.builder()
                .name("User")
                .build();
        RoleDto roleDto2 = RoleDto.builder()
                .name("Admin")
                .build();

        System.out.println(roleController.save(roleDto1));
        System.out.println(roleController.save(roleDto2));
        System.out.println(roleController.getById(0L));
        System.out.println(roleController.delete(1L));

        roleDto1.setId(0L);
        roleDto1.setName("Librarian");
        System.out.println(roleController.update(roleDto1));
        System.out.println(roleController.getById(0L));
        System.out.println("roleTest end");
    }

    private static void personTest(PersonController personController) throws JsonProcessingException {
        System.out.println("personTest start");
        PersonDto personDto1 = PersonDto.builder()
                .firstName("Michael")
                .lastName("Jackson")
                .email("MJ@gmail.com")
                .mobNum("+123456789")
                .locked(Boolean.FALSE)
                .enabled(Boolean.TRUE)
                .build();
        PersonDto personDto2 = PersonDto.builder()
                .firstName("Michael")
                .lastName("Jordan")
                .email("JM@gmail.com")
                .mobNum("+987654321")
                .locked(Boolean.FALSE)
                .enabled(Boolean.TRUE)
                .build();

        System.out.println(personController.save(personDto1));
        System.out.println(personController.save(personDto2));
        System.out.println(personController.getById(0L));
        System.out.println(personController.delete(1L));

        personDto1.setId(0L);
        personDto1.setLocked(Boolean.TRUE);
        System.out.println(personController.update(personDto1));
        System.out.println(personController.getById(0L));
        System.out.println("personTest end");
    }

    private static void personCredentialsTest(PersonCredentialsController personCredentialsController) throws JsonProcessingException {
        System.out.println("personCredentialsTest");
        PersonCredentialsDto personCredentialsDto1 = PersonCredentialsDto.builder()
                .login("qwerty")
                .password("password")
                .build();
        PersonCredentialsDto personCredentialsDto2 = PersonCredentialsDto.builder()
                .login("asd")
                .password("12345678")
                .build();

        System.out.println(personCredentialsController.save(personCredentialsDto1));
        System.out.println(personCredentialsController.save(personCredentialsDto2));
        System.out.println(personCredentialsController.getById(0L));
        System.out.println(personCredentialsController.delete(1L));

        personCredentialsDto1.setId(0L);
        personCredentialsDto1.setPassword("passw0rd");
        System.out.println(personCredentialsController.update(personCredentialsDto1));
        System.out.println(personCredentialsController.getById(0L));
        System.out.println("personCredentialsTest end");
    }

    private static void orderTest(OrderController orderController) throws JsonProcessingException {
        System.out.println("orderTest start");
        OrderDto orderDto1 = OrderDto.builder()
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .price(BigDecimal.valueOf(10.1))
                .build();
        OrderDto orderDto2 = OrderDto.builder()
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .price(BigDecimal.valueOf(12.0))
                .build();

        System.out.println(orderController.save(orderDto1));
        System.out.println(orderController.save(orderDto2));
        System.out.println(orderController.getById(0L));
        System.out.println(orderController.delete(1L));

        orderDto1.setId(0L);
        orderDto1.setPrice(BigDecimal.valueOf(1.5));
        System.out.println(orderController.update(orderDto1));
        System.out.println(orderController.getById(0L));
        System.out.println("orderTest end");
    }

    private static void genreTest(GenreController genreController) throws JsonProcessingException {
        System.out.println("genreTest start");
        GenreDto genreDto1 = GenreDto.builder()
                .name("Detective")
                .build();
        GenreDto genreDto2 = GenreDto.builder()
                .name("Fantasy")
                .build();

        System.out.println(genreController.save(genreDto1));
        System.out.println(genreController.save(genreDto2));
        System.out.println(genreController.getById(0L));
        System.out.println(genreController.delete(1L));

        genreDto1.setId(0L);
        genreDto1.setName("Action");
        System.out.println(genreController.update(genreDto1));
        System.out.println(genreController.getById(0L));
        System.out.println("genreTest end");
    }

    private static void bookItemTest(BookItemController bookItemController) throws JsonProcessingException {
        System.out.println("bookItemTest start");
        BookItemDto bookItemDto1 = BookItemDto.builder()
                .price(BigDecimal.valueOf(10.2))
                .build();
        BookItemDto bookItemDto2 = BookItemDto.builder()
                .price(BigDecimal.valueOf(7.5))
                .build();

        System.out.println(bookItemController.save(bookItemDto1));
        System.out.println(bookItemController.save(bookItemDto2));
        System.out.println(bookItemController.getById(0L));
        System.out.println(bookItemController.delete(1L));

        bookItemDto1.setId(0L);
        bookItemDto1.setPrice(BigDecimal.valueOf(9.1));
        System.out.println(bookItemController.update(bookItemDto1));
        System.out.println(bookItemController.getById(0L));
        System.out.println("bookItemTest end");
    }

    private static void bookTest(BookController bookController) throws JsonProcessingException {
        System.out.println("bookTest start");
        BookDto bookDto1 = BookDto.builder()
                .name("Stories of Sherlock Holmes")
                .description("some text")
                .picPath("Sherlock.png")
                .build();
        BookDto bookDto2 = BookDto.builder()
                .name("Harry Potter")
                .description("some wizard stuff")
                .picPath("Harry.png")
                .build();

        System.out.println(bookController.save(bookDto1));
        System.out.println(bookController.save(bookDto2));
        System.out.println(bookController.getById(0L));
        System.out.println(bookController.delete(1L));

        bookDto1.setId(0L);
        bookDto1.setName("Sherlock Holmes");
        System.out.println(bookController.update(bookDto1));
        System.out.println(bookController.getById(0L));
        System.out.println("bookTest end");
    }

    private static void authorTest(AuthorController authorController) throws JsonProcessingException {
        System.out.println("authorTest start");
        AuthorDto authorDto1 = AuthorDto.builder()
                .name("Joanne Rowling")
                .build();
        AuthorDto authorDto2 = AuthorDto.builder()
                .name("Arthur Conan Doyle")
                .build();

        System.out.println(authorController.save(authorDto1));
        System.out.println(authorController.save(authorDto2));
        System.out.println(authorController.getById(0L));
        System.out.println(authorController.delete(1L));

        authorDto1.setId(0L);
        authorDto1.setName("J.K.Rowling");
        System.out.println(authorController.update(authorDto1));
        System.out.println(authorController.getById(0L));
        System.out.println("authorTest end");
    }
}
