package by.ginel.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "by.ginel", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = EnableWebMvc.class)})
public class AppConfig {
    @Profile("build")
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
