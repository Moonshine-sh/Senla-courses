package by.ginel.service.impl;

import by.ginel.dao.BookItemDao;
import by.ginel.dto.BookItemDto;
import by.ginel.mapper.BookItemMapper;
import by.ginel.service.BookItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class BookItemServiceImpl implements BookItemService {
    private final BookItemMapper bookItemMapper;
    private final BookItemDao bookItemDao;

    @Override
    public List<BookItemDto> getAll() {
        return bookItemDao.getAll().stream().map(bookItemMapper::mapToBookItemDto).toList();
    }

    @Override
    public BookItemDto getById(Long id) {
        return bookItemMapper.mapToBookItemDto(bookItemDao.getById(id));
    }

    @Override
    public BookItemDto save(BookItemDto entityDto) {
        return bookItemMapper.mapToBookItemDto(bookItemDao.save(bookItemMapper.mapToBookItem(entityDto)));
    }

    @Override
    public void delete(Long id) {
        bookItemDao.delete(id);
    }

    @Override
    public BookItemDto update(BookItemDto entityDto) {
        return bookItemMapper.mapToBookItemDto(bookItemDao.update(bookItemMapper.mapToBookItem(entityDto)));
    }
}
