package edu.scoutsPoo.webApp.repositories;

import edu.scoutsPoo.webApp.entities.Participacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipacionRepository extends JpaRepository<Participacion, Long> {

    List<Participacion> findByActividadId(Long actividadId);

    List<Participacion> findByScoutId(Long scoutId);

    void deleteByScoutId(Long scoutId);

    boolean existsByScoutIdAndActividadId(Long scoutId, Long actividadId);

  }
