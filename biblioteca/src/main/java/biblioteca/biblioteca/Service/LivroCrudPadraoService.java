package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class LivroCrudPadraoService{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;


    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto){ 
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);      
    }

    @Override
    public List<LivroResponseDto> buscarLivroPorIsbn(Long isbn){
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarPorTitulo(String titulo){        
    }

    @Override
    public LivroResponseDto atualizarLivro(Long isbn, LivroPostPutDto livroPostPutDto){
        Livro livro = obterLivroExistente(isbn);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAutor(livroPostPutDto.getAutor());
        livro.setAno(livroPostPutDto.getAno());
        livro.getEdicao(livroPostPutDto.getEdicao());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(Long isbn){
        livroRepository.delete(obterLivroExistente(isbn));
    }

    public Livro obterLivroExistente(Long isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

}
