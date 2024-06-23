package trabbrendavane.trabbrendavane.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trabbrendavane.trabbrendavane.domain.model.Livro;
import trabbrendavane.trabbrendavane.domain.model.Usuario;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByUsuario(Usuario usuario);
    
}
