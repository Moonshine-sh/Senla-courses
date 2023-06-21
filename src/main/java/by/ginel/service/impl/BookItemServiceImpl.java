package by.ginel.service.impl;

import by.ginel.dao.BookItemDao;
import by.ginel.dto.BookItemDto;
import by.ginel.entity.BookItem;
import by.ginel.mapper.BookItemMapper;
import by.ginel.service.BookItemService;
import by.ginel.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookItemServiceImpl implements BookItemService {
    private final BookItemMapper bookItemMapper;
    private final BookItemDao bookItemDao;

    @Override
    public List<BookItemDto> getAll(Pageable pageable) {
        List<BookItem> bookItems = bookItemDao.getAll(pageable);
        return bookItems
                .stream()
                .map(bookItemMapper::mapToBookItemDto)
                .toList();
    }

    @Override
    public List<BookItemDto> getAll() {
        return getAll(Pageable.maxPage());
    }

    @Override
    public BookItemDto getById(Long id) {
        BookItem bookItem = bookItemDao.getById(id);
        return bookItemMapper.mapToBookItemDto(bookItem);
    }

    @Override
    public BookItemDto save(BookItemDto entityDto) {
        BookItem bookItem = bookItemDao.save(bookItemMapper.mapToBookItem(entityDto));
        return bookItemMapper.mapToBookItemDto(bookItem);
    }

    @Override
    public void delete(Long id) {
        bookItemDao.delete(id);
    }

    @Override
    public BookItemDto update(BookItemDto entityDto) {
        BookItem bookItem = bookItemDao.update(bookItemMapper.mapToBookItem(entityDto));
        return bookItemMapper.mapToBookItemDto(bookItem);
    }
}
