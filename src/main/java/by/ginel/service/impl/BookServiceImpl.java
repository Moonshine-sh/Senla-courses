package by.ginel.service.impl;

import by.ginel.dao.BookDao;
import by.ginel.dto.BookDto;
import by.ginel.mapper.BookMapper;
import by.ginel.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookDao bookDao;

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll().stream().map(bookMapper::mapToBookDto).toList();
    }

    @Override
    public BookDto getById(Long id) {
        return bookMapper.mapToBookDto(bookDao.getById(id));
    }

    @SneakyThrows
    @Override
    public BookDto save(BookDto entityDto) {
        return bookMapper.mapToBookDto(bookDao.save(bookMapper.mapToBook(entityDto)));
    }

    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }

    @Override
    public BookDto update(BookDto entityDto) {
        return bookMapper.mapToBookDto(bookDao.update(bookMapper.mapToBook(entityDto)));
    }
}
