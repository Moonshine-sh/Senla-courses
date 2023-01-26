package by.ginel.dao.impl;

import by.ginel.dao.PersonDao;
import by.ginel.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDaoImpl extends DaoImpl<Person> implements PersonDao {
    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }
}
