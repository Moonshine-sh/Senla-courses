package by.ginel.dao;

import by.ginel.entity.Author;

public interface AuthorDao extends Dao<Author>{
    Author findByName(String name);
}
