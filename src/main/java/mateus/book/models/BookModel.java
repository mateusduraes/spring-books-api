package mateus.book.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "book")
public class BookModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int year;

    @ManyToOne(optional=false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    @JsonBackReference
    private AuthorModel author;
}
