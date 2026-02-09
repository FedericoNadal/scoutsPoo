package edu.scoutsPoo.webApp.repositories;

import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByScout(Scout scout);

    boolean existsByUsername(String username);
}
