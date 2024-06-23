package trabbrendavane.trabbrendavane.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import trabbrendavane.trabbrendavane.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
       
    Optional<Usuario> findByEmail(String email);
}