package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;

import java.util.List;

public interface AutorCrudService {
 
    LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto);

    List<LivroResponseDto> buscarLivroPorIsbn(Long isbn);

    List<LivroResponseDto> buscarPorTitulo(String titulo);

    LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto);

    void removerLivro(Long id);

}