package edu.scoutsPoo.webApp.repositories;

import edu.scoutsPoo.webApp.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    Optional<Grupo> findByCodigo(long codigo);
    Optional<Grupo> findByDenominacion(String denominacion);
    
    boolean existsByCodigo(int codigo);
    boolean existsByDenominacion(String denominacion);

    
}
