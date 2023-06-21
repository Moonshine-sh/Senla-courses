package by.ginel.service.impl;

import by.ginel.dao.BookDao;
import by.ginel.dto.BookDto;
import by.ginel.entity.Book;
import by.ginel.entity.Genre;
import by.ginel.mapper.BookMapper;
import by.ginel.service.BookService;
import by.ginel.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookDao bookDao;

    @Override
    public List<BookDto> getAll(Pageable pageable) {
        List<Book> books = bookDao.getAll(pageable);
        return books
                .stream()
                .map(bookMapper::mapToBookDto)
                .toList();
    }

    @Override
    public List<BookDto> getAll() {
        return getAll(Pageable.maxPage());
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookDao.getById(id);
        return bookMapper.mapToBookDto(book);
    }

    @Override
    public BookDto save(BookDto entityDto) {
        Book book = bookDao.save(bookMapper.mapToBook(entityDto));
        return bookMapper.mapToBookDto(book);
    }

    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }

    @Override
    public BookDto update(BookDto entityDto) {
        Book book = bookDao.update(bookMapper.mapToBook(entityDto));
        return bookMapper.mapToBookDto(book);
    }

    @Override
    public List<BookDto> getAll(Map<String, String> params, Pageable pageable) {
        List<Book> books = bookDao.getAll(params, pageable);
        return books
                .stream()
                .map(bookMapper::mapToBookDto)
                .toList();
    }

    @Override
    public List<BookDto> findAllByName(String name) {
        log.info("Executing method findAllByName()");

        List<Book> books = bookDao.findAllByName(name);
        return books
                .stream()
                .map(bookMapper::mapToBookDto)
                .toList();
    }

    @Override
    public List<BookDto> findAllByAuthor(String author) {
        log.info("Executing method findAllByAuthor()");

        List<Book> books = bookDao.findAllByAuthor(author);
        return books
                .stream()
                .map(bookMapper::mapToBookDto)
                .toList();
    }

    @Override
    public List<BookDto> findAllByGenre(Genre genre) {
        log.info("Executing method findAllByGenre()");

        List<Book> books = bookDao.findAllByGenre(genre);
        return books
                .stream()
                .map(bookMapper::mapToBookDto)
                .toList();
    }
}
