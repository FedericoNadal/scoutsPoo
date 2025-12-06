package edu.scoutsPoo.webApp.repositories;

import edu.scoutsPoo.webApp.entities.Scout;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface ScoutRepository extends JpaRepository<Scout, Long> {

    List<Scout> findByGrupoCodigo(Long grupoId);

    List<Scout> findByComunidadNumero(Long comunidadId);

    List<Scout> findBySedeCodigo(Long sedeId);

    //List<Scout> findByApellidoContainingIgnoreCase(String apellido);

    boolean existsByApodo(String apodo);

    Optional <Scout> findByApodo(String apodo);
    Optional <Scout> findById(Long id);
    boolean existsById(Long id);


    }
