package by.ginel.service.impl;

import by.ginel.dao.BookItemDao;
import by.ginel.dto.BookItemDto;
import by.ginel.mapper.BookItemMapper;
import by.ginel.service.BookItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class BookItemServiceImpl implements BookItemService {
    private final BookItemMapper bookItemMapper;
    private final BookItemDao bookItemDao;

    @Override
    public List<BookItemDto> getAll() throws SQLException, InterruptedException {
        return bookItemDao.getAll().stream().map(bookItemMapper::mapToBookItemDto).toList();
    }

    @Override
    public BookItemDto getById(Long id) throws SQLException, InterruptedException {
        return bookItemMapper.mapToBookItemDto(bookItemDao.getById(id));
    }

    @Override
    public BookItemDto save(BookItemDto entityDto) throws SQLException, InterruptedException {
        bookItemDao.save(bookItemMapper.mapToBookItem(entityDto));
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        bookItemDao.delete(id);
    }

    @Override
    public BookItemDto update(BookItemDto entityDto) throws SQLException, InterruptedException {
        bookItemDao.update(bookItemMapper.mapToBookItem(entityDto));
        return null;
    }
}
