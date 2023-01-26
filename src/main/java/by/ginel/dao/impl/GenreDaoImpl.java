package by.ginel.dao.impl;

import by.ginel.dao.GenreDao;
import by.ginel.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreDaoImpl extends DaoImpl<Genre> implements GenreDao {
    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }
}
