package by.ginel.dao.impl;

import by.ginel.dao.PersonDao;
import by.ginel.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDaoImpl extends AbstractDaoImpl<Person> implements PersonDao {

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }
}
