package by.ginel.dao.impl;

import by.ginel.dao.PersonCredentialsDao;
import by.ginel.entity.PersonCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonCredentialsDaoImpl extends AbstractDaoImpl<PersonCredentials> implements PersonCredentialsDao {

    @Override
    protected Class<PersonCredentials> getEntityClass() {
        return PersonCredentials.class;
    }
}
