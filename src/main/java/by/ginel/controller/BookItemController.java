package by.ginel.controller;

import by.ginel.dto.BookItemDto;
import by.ginel.service.BookItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-items")
public class BookItemController {
    private final BookItemService bookItemService;

    @GetMapping
    public List<BookItemDto> getAll() {
        return bookItemService.getAll();
    }

    @GetMapping("/{id}")
    public BookItemDto getById(@PathVariable Long id) {
        return bookItemService.getById(id);
    }

    @PostMapping
    public BookItemDto save(@RequestBody BookItemDto bookItemDto) {
        return bookItemService.save(bookItemDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookItemService.delete(id);
    }

    @PutMapping
    public BookItemDto update(@RequestBody BookItemDto bookItemDto) {
        return bookItemService.update(bookItemDto);
    }
}
