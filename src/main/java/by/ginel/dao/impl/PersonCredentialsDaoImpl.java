package by.ginel.dao.impl;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.dao.datasource.MockDataSource;
import by.ginel.entity.PersonCredentials;
import org.springframework.stereotype.Component;

@Component
public class PersonCredentialsDaoImpl extends DaoImpl<PersonCredentials> implements PersonCredentialsDao {
    public PersonCredentialsDaoImpl(MockDataSource mockDataSource) {
        super(mockDataSource);
    }

    @Override
    protected Class<PersonCredentials> getEntityClass() {
        return PersonCredentials.class;
    }
}
