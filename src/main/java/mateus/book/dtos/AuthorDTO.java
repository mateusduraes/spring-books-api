package mateus.book.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorDTO {
    @NotBlank
    private String name;

}
