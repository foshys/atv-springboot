package biblioteca.biblioteca.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@Table(name = "tb_livro")
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @Column(name = "ds_isbn")
    @JsonProperty("isbn")
    private String isbn;

    @Column(name = "ds_titulo", nullable = false)
    @JsonProperty("titulo")
    private String titulo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor", nullable = false)
    @JsonProperty("autor")
    private Autor autor;

    @Column(name = "nr_ano", nullable = false)
    @JsonProperty("ano")
    private int ano;

    @Column(name = "ds_edicao", nullable = false)
    @JsonProperty("edicao")
    private String edicao;
}