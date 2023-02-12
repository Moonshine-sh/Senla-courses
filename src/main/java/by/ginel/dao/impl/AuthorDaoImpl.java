package by.ginel.dao.impl;

import by.ginel.dao.AuthorDao;
import by.ginel.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorDaoImpl extends AbstractDaoImpl<Author> implements AuthorDao {

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }
}
