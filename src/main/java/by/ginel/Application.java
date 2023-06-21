package by.ginel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "by.ginel")
@OpenAPIDefinition
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
