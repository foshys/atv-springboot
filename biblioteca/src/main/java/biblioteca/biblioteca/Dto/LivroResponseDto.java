package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class AutorResponseDto {

    @JsonProperty("isbn")
    private Long isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("autor")
    private Autor autor;

    @JsonProperty("ano")
    private int ano;

    @JsonProperty("edicao")
    private String edicao;
    
}