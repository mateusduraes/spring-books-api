package mateus.book.repositories;

import mateus.book.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID>  {
}
