package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
        if (livroRepository.existsById(dto.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um livro com esse ISBN");
        }
        Livro livro = Livro.builder()
                .isbn(dto.getIsbn())
                .titulo(dto.getTitulo())
                .autor(obterAutorExistente(dto.getAutorId()))
                .ano(dto.getAno())
                .edicao(dto.getEdicao())
                .build();
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    // isbn e titulo são opcionais; vazio ("") casa com qualquer valor
    @Override
    public List<LivroResponseDto> buscarLivros(String isbn, String titulo) {
        String isbnBusca = isbn == null ? "" : isbn.trim();
        String tituloBusca = titulo == null ? "" : titulo.trim();
        return livroRepository
                .findByIsbnContainingAndTituloContainingIgnoreCase(isbnBusca, tituloBusca)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivroPorIsbn(String isbn) {
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto dto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(obterAutorExistente(dto.getAutorId()));
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(String isbn) {
        livroRepository.delete(obterLivroExistente(isbn));
    }

    private Livro obterLivroExistente(String isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }
}