package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("autor")
    private AutorResponseDto autor;

    @JsonProperty("ano")
    private int ano;

    @JsonProperty("edicao")
    private String edicao;
}