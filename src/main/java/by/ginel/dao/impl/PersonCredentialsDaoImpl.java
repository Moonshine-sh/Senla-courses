package by.ginel.dao.impl;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.entity.PersonCredentials;
import org.springframework.stereotype.Component;

@Component
public class PersonCredentialsDaoImpl extends DaoImpl<PersonCredentials> implements PersonCredentialsDao {
    @Override
    protected Class<PersonCredentials> getEntityClass() {
        return PersonCredentials.class;
    }
}
