package trabbrendavane.trabbrendavane.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import trabbrendavane.trabbrendavane.domain.dto.usuario.UsuarioRequestDTO;
import trabbrendavane.trabbrendavane.domain.dto.usuario.UsuarioResponseDTO;
import trabbrendavane.trabbrendavane.domain.exception.ResourceBadRequestException;
import trabbrendavane.trabbrendavane.domain.exception.ResourceNotFoundException;
import trabbrendavane.trabbrendavane.domain.model.Usuario;
import trabbrendavane.trabbrendavane.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements ICRUDService<UsuarioRequestDTO, UsuarioResponseDTO>{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UsuarioResponseDTO> obterTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obterPorId(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isEmpty()){
           throw new ResourceNotFoundException("não foi possível encontar o usuário com o id" +id);
        }
        return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if(dto.getEmail() == null || dto.getSenha() == null){
            throw new ResourceBadRequestException("Email e Senha são Obrigatórios!");
        }
        Optional<Usuario> optUsuario = 
        usuarioRepository.findByEmail(dto.getEmail());
        if(optUsuario.isPresent()){
            throw new ResourceBadRequestException("Já existe um usuário cadastrado com esse email!");     
        }
        Usuario usuario = mapper.map(dto, Usuario.class);
        String senha = bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senha);
        usuario.setId(null);
        usuario.setDataCadastro(new Date());
        usuario = usuarioRepository.save(usuario);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuarioBanco = obterPorId(id);
        if(dto.getEmail() == null || dto.getSenha() == null){
            throw new ResourceBadRequestException("Email e Senha são Obrigatórios!");
          }
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setSenha(dto.getSenha());
        usuario.setId(id);
        usuario.setDataCadastro(usuarioBanco.getDataCadastro());
        usuario.setDataInativacao(usuarioBanco.getDataInativacao());
        usuario = usuarioRepository.save(usuario);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isEmpty()){
            throw new 
            ResourceNotFoundException("Nao foi possivel encontrar o usuario de id: " +id);
        }
        Usuario usuario = optUsuario.get();
        usuario.setDataInativacao(new Date());
        usuarioRepository.save(usuario);
    }
    
}
