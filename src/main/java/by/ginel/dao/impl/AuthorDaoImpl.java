package by.ginel.dao.impl;

import by.ginel.dao.AuthorDao;
import by.ginel.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl extends DaoImpl<Author> implements AuthorDao {
    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }
}
