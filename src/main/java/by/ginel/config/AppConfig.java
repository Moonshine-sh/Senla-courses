package by.ginel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@ComponentScan("by.ginel")
public class AppConfig {
    private final Environment env;
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setJavaMailProperties(getProperties());
//        return javaMailSender;
//    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("spring.mail.host", env.getProperty("spring.mail.host"));
        properties.put("spring.mail.port", env.getProperty("spring.mail.port", Integer.class));
        properties.put("spring.mail.username", env.getProperty("spring.mail.username"));
        properties.put("spring.mail.password", env.getProperty("spring.mail.password"));
        properties.put("spring.mail.properties.mail.transport.protocol", env.getProperty("spring.mail.properties.mail.transport.protocol"));
        properties.put("spring.mail.properties.mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.put("spring.mail.properties.mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        return properties;
    }

}
