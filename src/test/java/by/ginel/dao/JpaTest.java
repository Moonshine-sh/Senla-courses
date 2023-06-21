package by.ginel.dao;

import by.ginel.Main;
import by.ginel.config.DatabaseConfig;
import by.ginel.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Main.class, DatabaseConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@Testcontainers
public class JpaTest {
    @Autowired
    @Container
    private PostgreSQLContainer postgreSQLContainer;

    @Autowired
    private SDJpaDao sdJpaDao;

    @Test
    public void test_testcontainer(){
        Page<Person> all = sdJpaDao.findAll(Pageable.ofSize(10));
        Assert.assertFalse(all.hasNext());
    }
}
