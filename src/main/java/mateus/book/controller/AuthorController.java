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

}
