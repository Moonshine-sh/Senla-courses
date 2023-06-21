package by.ginel.service;

import by.ginel.dto.BookDto;
import by.ginel.entity.Genre;
import by.ginel.util.Pageable;

import java.util.List;
import java.util.Map;

public interface BookService extends Service<BookDto> {
    List<BookDto> getAll(Map<String,String> params, Pageable pageable);
    List<BookDto> findAllByName(String name);

    List<BookDto> findAllByAuthor(String author);

    List<BookDto> findAllByGenre(Genre genre);
}
