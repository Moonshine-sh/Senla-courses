package by.ginel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"by.ginel.service", "by.ginel.mapper"})
public class ServiceConfig {
}
