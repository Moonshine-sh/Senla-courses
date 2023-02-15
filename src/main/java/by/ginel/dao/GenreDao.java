package by.ginel.dao;

import by.ginel.entity.Genre;

public interface GenreDao extends Dao<Genre>{
    Genre findByName(String name);
}
