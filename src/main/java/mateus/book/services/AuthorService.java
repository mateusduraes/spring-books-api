package mateus.book.services;

import mateus.book.models.AuthorModel;
import mateus.book.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public AuthorModel save(AuthorModel authorModel) {
        return authorRepository.save(authorModel);
    }

    public List<AuthorModel> findAll() {
        return authorRepository.findAll();
    }

    public Optional<AuthorModel> findById(UUID id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public void deleteAuthorById(UUID id) {
        authorRepository.deleteById(id);
    }
}
