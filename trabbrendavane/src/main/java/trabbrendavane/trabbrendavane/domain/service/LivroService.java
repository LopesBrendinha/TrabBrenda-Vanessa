package trabbrendavane.trabbrendavane.domain.service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import trabbrendavane.trabbrendavane.domain.dto.livro.LivroRequestDTO;
import trabbrendavane.trabbrendavane.domain.dto.livro.LivroResponseDTO;
import trabbrendavane.trabbrendavane.domain.exception.ResourceBadRequestException;
import trabbrendavane.trabbrendavane.domain.exception.ResourceNotFoundException;
import trabbrendavane.trabbrendavane.domain.model.Livro;
import trabbrendavane.trabbrendavane.domain.model.Usuario;
import trabbrendavane.trabbrendavane.domain.repository.LivroRepository;

@Service
public class LivroService implements ICRUDService<LivroRequestDTO,
LivroResponseDTO>{
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private ModelMapper mapper;


    @Override
    public List<LivroResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Livro> livros = livroRepository.findByUsuario(usuario);
        return livros.stream().map(livro -> mapper.map(livro, LivroResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LivroResponseDTO obterPorId(Long id) {
        Optional<Livro> optLivro = livroRepository.findById(id);
        if(optLivro.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o livro com o id: "+id);
        }
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();
        if(optLivro.get().getUsuario().getId() != usuario.getId()){
            throw new ResourceNotFoundException("O livro de id: " +
            id + "não pertence a esse usuário");
        }
        return mapper.map(optLivro, LivroResponseDTO.class);
    }

    @Override
    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {
        validarLivro(dto);
        Livro livro = mapper.map(dto, Livro.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        livro.setUsuario(usuario);
        livro.setId(null);
        livro = livroRepository.save(livro);
        return mapper.map(livro, LivroResponseDTO.class);
    }

    @Override
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        obterPorId(id);
        validarLivro(dto);
        Livro livro = mapper.map(dto, Livro.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       livro.setUsuario(usuario);
       livro.setId(id);
       livro = livroRepository.save(livro);
       return mapper.map(livro, LivroResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
       obterPorId(id);
       livroRepository.deleteById(id);
    }

    private void validarLivro(LivroRequestDTO dto){
        if((dto.getGenero() == null) || (dto.getTitulo() == null)){
            throw new ResourceBadRequestException("Título Inválido - Campos Obrigatórios");
        }
    }
    
}
