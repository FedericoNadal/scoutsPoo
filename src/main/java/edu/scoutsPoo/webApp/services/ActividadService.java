package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Actividad;
import edu.scoutsPoo.webApp.repositories.ActividadRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.scoutsPoo.webApp.DTOs.ActividadDto;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }
public Actividad create(Actividad actividad) {
    return actividadRepository.save(actividad);
}

    public List<Actividad> findAll() {
        return actividadRepository.findAll();
    }

   public Optional<Actividad> findById(Long id) {
    return actividadRepository.findById(id);
}

    public void delete(Long id) {
        if (actividadRepository.existsById(id)) {
            actividadRepository.deleteById(id);
        }
    }

    public boolean existsById(Long id) {
        return actividadRepository.existsById(id);
      }

    // UPDATE
    
@Transactional
public Actividad update(long id, String descripcionNueva) {
    Optional<Actividad> optionalActividad = findById(id);

    // Manejar el caso en que no exista
    if (optionalActividad.isEmpty()) {
        throw new RuntimeException("Actividad no encontrada con id: " + id);
    }

    Actividad actividadExistente = optionalActividad.get();
    actividadExistente.setDescripcion(descripcionNueva);
    return actividadRepository.save(actividadExistente);
}
}
