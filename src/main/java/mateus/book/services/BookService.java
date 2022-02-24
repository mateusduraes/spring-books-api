package mateus.book.services;

import mateus.book.models.BookModel;
import mateus.book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Transactional
    public BookModel save(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }


    public boolean existsByName(String bookName) {
        return bookRepository.existsByName(bookName);
    }

    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }

    public Optional<BookModel> findById(UUID id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public void deleteBookById(UUID id) {
      bookRepository.deleteById(id);
    }
}
