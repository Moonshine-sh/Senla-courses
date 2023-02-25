package by.ginel;

import by.ginel.config.AppConfig;
import by.ginel.dao.GenreDao;
import by.ginel.entity.Genre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class MvcTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private GenreDao genreDao;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void webAppContextTest() throws Exception {
        Assert.assertTrue(webApplicationContext.getServletContext() instanceof MockServletContext);
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/genre/")).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Transactional
    @Test
    public void getByIdTest() throws Exception {
        Genre genre = Genre.builder()
                .name("ACTION")
                .build();

        Genre saved = genreDao.save(genre);

        mockMvc.perform(get("/genre/" + saved.getId()))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(jsonPath("$.name").value(saved.getName()));
    }

    @Transactional
    @Test
    public void deleteTest() throws Exception {
        Genre genre = Genre.builder()
                .name("ACTION")
                .build();

        Genre saved = genreDao.save(genre);

        mockMvc.perform(delete("/genre/delete/" + saved.getId()))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        Assert.assertEquals(genreDao.getAll().size(), 0);
    }

    @Transactional
    @Test
    public void updateTest() throws Exception {
        Genre genre = Genre.builder()
                .name("ACTION")
                .build();

        Genre saved = genreDao.save(genre);

        String genreNew = String.format("""
                {
                "id":%d,
                "name":"Detective"
                }
                """, saved.getId()
        );

        mockMvc.perform(put("/genre/update")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(genreNew))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        Genre updatedGenre = genreDao.getById(saved.getId());

        Assert.assertEquals(updatedGenre.getName(), "Detective");
    }

    @Transactional
    @Test
    public void saveTest() throws Exception {
        String genre = """
                {
                "id":null,
                "name":"Detective"
                }
                """;

        mockMvc.perform(post("/genre/save")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(genre))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
}