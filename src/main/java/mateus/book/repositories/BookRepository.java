package mateus.book.repositories;

import mateus.book.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {
    boolean existsByName(String name);
}
