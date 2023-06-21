package by.ginel.controller;

import by.ginel.dto.BookDto;
import by.ginel.service.BookService;
import by.ginel.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam Optional<Map<String ,String>> params, @RequestParam Pageable pageable) {
        List<BookDto> allBooks = params.map(param -> bookService.getAll(param, pageable))
                                       .orElseGet(() -> bookService.getAll(pageable));

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        BookDto bookDto = bookService.getById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<BookDto> save(@RequestBody BookDto bookDto) {
        BookDto savedBook = bookService.save(bookDto);
        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) {
        BookDto updatedBook = bookService.update(bookDto);
        return ResponseEntity.ok(updatedBook);
    }
}
