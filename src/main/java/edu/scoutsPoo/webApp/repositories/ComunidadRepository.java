package edu.scoutsPoo.webApp.repositories;

import edu.scoutsPoo.webApp.entities.Comunidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ComunidadRepository extends JpaRepository<Comunidad, Long> {

    //List<Comunidad> findBySedesId(Long sedeCodigo);  // por sede
    Optional <Comunidad> findByNumero(Long numero);
    List<Comunidad> findByActividadPrincipal(String actividadPrincipal);

    boolean existsByNumero(long numero);
}
