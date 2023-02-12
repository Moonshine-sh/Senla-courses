package by.ginel.service.impl;

import by.ginel.dao.BookDao;
import by.ginel.dto.BookDto;
import by.ginel.mapper.BookMapper;
import by.ginel.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookDao bookDao;

    @Override
    public List<BookDto> getAll() throws SQLException, InterruptedException {
        return bookDao.getAll().stream().map(bookMapper::mapToBookDto).toList();
    }

    @Override
    public BookDto getById(Long id) throws SQLException, InterruptedException {
        return bookMapper.mapToBookDto(bookDao.getById(id));
    }

    @SneakyThrows
    @Override
    public Long save(BookDto entityDto) throws SQLException {
        bookDao.save(bookMapper.mapToBook(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        bookDao.delete(id);
    }

    @Override
    public void update(BookDto entityDto) throws SQLException, InterruptedException {
        bookDao.update(bookMapper.mapToBook(entityDto));
    }
}
