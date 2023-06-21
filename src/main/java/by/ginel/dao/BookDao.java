package by.ginel.dao;

import by.ginel.entity.Book;
import by.ginel.entity.Genre;
import by.ginel.util.Pageable;

import java.util.List;
import java.util.Map;

public interface BookDao extends Dao<Book> {
    List<Book> getAll(Map<String, String> params, Pageable pageable);

    List<Book> findAllByName(String name);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByGenre(Genre genre);
}
