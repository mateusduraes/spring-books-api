package mateus.book.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
public class BookDTO {

    @NotBlank
    private String name;

    @NotNull
    private int year;

    @NotNull
    private UUID authorId;

}
