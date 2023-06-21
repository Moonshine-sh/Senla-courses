package by.ginel.controller;

import by.ginel.dto.BookDto;
import by.ginel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping
    public BookDto save(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping
    public BookDto update(@RequestBody BookDto bookDto) {
        return bookService.update(bookDto);
    }
}
