package by.ginel.dao.impl;

import by.ginel.dao.GenreDao;
import by.ginel.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreDaoImpl extends AbstractDaoImpl<Genre> implements GenreDao {

    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }
}
