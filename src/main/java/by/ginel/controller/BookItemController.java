package by.ginel.controller;

import by.ginel.dto.BookItemDto;
import by.ginel.service.BookItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-items")
public class BookItemController {
    private final BookItemService bookItemService;

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping
    public List<BookItemDto> getAll() {
        return bookItemService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/{id}")
    public BookItemDto getById(@PathVariable Long id) {
        return bookItemService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping
    public BookItemDto save(@RequestBody BookItemDto bookItemDto) {
        return bookItemService.save(bookItemDto);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookItemService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping
    public BookItemDto update(@RequestBody BookItemDto bookItemDto) {
        return bookItemService.update(bookItemDto);
    }
}
