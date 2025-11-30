package edu.scoutsPoo.webApp.repositories;

import edu.scoutsPoo.webApp.entities.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    
    List<Actividad> findByDescripcion(String descripcion);
}
