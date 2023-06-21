package by.ginel.dao;

import by.ginel.entity.Person;

import java.util.List;

public interface PersonDao extends Dao<Person> {

    List<Person> findAllByName(String name);
}
