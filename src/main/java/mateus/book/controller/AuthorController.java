package mateus.book.controller;


import mateus.book.dtos.AuthorDTO;
import mateus.book.models.AuthorModel;
import mateus.book.services.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody AuthorDTO authorDTO) {
        AuthorModel authorModel = new AuthorModel();
        BeanUtils.copyProperties(authorDTO, authorModel);
        return ResponseEntity.status(HttpStatus.OK).body(authorService.save(authorModel));
    }

    @GetMapping
    public ResponseEntity<List<AuthorModel>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable UUID id) {
        Optional<AuthorModel> optionalAuthorModel = authorService.findById(id);
        if (!optionalAuthorModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalAuthorModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable UUID id) {
        Optional<AuthorModel> optionalAuthorModel = authorService.findById(id);
        if (!optionalAuthorModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author not found");
        }
        authorService.deleteAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable UUID id, @RequestBody AuthorDTO authorDTO) {
        Optional<AuthorModel> optionalAuthorModel = authorService.findById(id);
        if (!optionalAuthorModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not found");
        }

        var authorModel = new AuthorModel();
        BeanUtils.copyProperties(authorDTO, authorModel);
        authorModel.setId(optionalAuthorModel.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(authorService.save(authorModel));
    }

}
