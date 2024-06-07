package trabbrendavane.trabbrendavane.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import trabbrendavane.trabbrendavane.domain.model.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{    
    Optional<Usuario> findByEmail(String email);
    
}
