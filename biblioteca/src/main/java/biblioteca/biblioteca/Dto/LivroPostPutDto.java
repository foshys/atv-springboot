package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {

    @JsonProperty("isbn")
    @NotBlank(message = "O ISBN não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "O título do livro não pode ser vazio")
    private String titulo;

    @JsonProperty("autorId")
    @NotNull(message = "O id do autor não pode ser vazio")
    private Long autorId;

    @JsonProperty("ano")
    @NotNull(message = "O ano do livro não pode ser vazio")
    @Positive(message = "O ano deve ser positivo")
    private Integer ano;

    @JsonProperty("edicao")
    @NotBlank(message = "A edição do livro não pode ser vazia")
    private String edicao;
}