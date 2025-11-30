package edu.scoutsPoo.webApp.repositories;

import edu.scoutsPoo.webApp.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Long> {

    Sede findByCodigo(long codigo);

    List<Sede> findByProvincia(String provincia);

    List<Sede> findByLocalidadContainingIgnoreCase(String localidad);

    boolean existsByNombre(String nombre);
}
