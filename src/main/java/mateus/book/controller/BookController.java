package mateus.book.controller;

import mateus.book.dtos.BookDTO;
import mateus.book.models.AuthorModel;
import mateus.book.models.BookModel;
import mateus.book.services.AuthorService;
import mateus.book.services.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @PostMapping()
    public ResponseEntity<Object> createBook(@RequestBody @Valid BookDTO bookDTO) {
        if (bookService.existsByName(bookDTO.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A book with this name already exists");
        }

        BookModel bookModel = new BookModel();
        BeanUtils.copyProperties(bookDTO, bookModel);

        Optional<AuthorModel> authorModel = authorService.findById(bookDTO.getAuthorId());
        if (!authorModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The provided author doesn't exist");
        }
        bookModel.setAuthor(authorModel.get());

        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(bookModel));
    }

    @GetMapping()
    public ResponseEntity<List<BookModel>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable UUID id) {
        Optional<BookModel> optionalBookModel = bookService.findById(id);
        if (!optionalBookModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalBookModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable UUID id) {
        Optional<BookModel> optionalBookModel = bookService.findById(id);
        if (!optionalBookModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not found");
        }
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@RequestBody @Valid BookDTO bookDTO, @PathVariable UUID id) {
        Optional<BookModel> optionalBookModel = bookService.findById(id);
        if (!optionalBookModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not found");
        }

        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookDTO, bookModel);
        bookModel.setId(optionalBookModel.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(bookModel));
    }
}
