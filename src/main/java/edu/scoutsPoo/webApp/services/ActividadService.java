package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Actividad;
import edu.scoutsPoo.webApp.repositories.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public Actividad create(String descripcion) {
        Actividad actividad = new Actividad(descripcion);
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
}
