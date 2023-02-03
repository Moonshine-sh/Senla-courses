package by.ginel.dao.impl;

import by.ginel.dao.AuthorDao;
import by.ginel.dao.datasource.MockDataSource;
import by.ginel.entity.Author;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl extends DaoImpl<Author> implements AuthorDao {
    public AuthorDaoImpl(MockDataSource mockDataSource) {
        super(mockDataSource);
    }

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }
}
