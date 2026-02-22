package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Actividad;
import edu.scoutsPoo.webApp.entities.Participacion;
import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.entities.Usuario;
import edu.scoutsPoo.webApp.repositories.ActividadRepository;
import edu.scoutsPoo.webApp.repositories.ScoutRepository;
import edu.scoutsPoo.webApp.repositories.ParticipacionRepository;
import jakarta.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;


import java.util.List;
import java.util.Optional;

import edu.scoutsPoo.webApp.DTOs.ActividadDto;
import edu.scoutsPoo.webApp.DTOs.MisActividadesDto;

@Service
public class ActividadService {
    

private final ActividadRepository actividadRepository;
private final ScoutRepository scoutRepository;
private final ParticipacionRepository participacionRepository;

public ActividadService(
        ActividadRepository actividadRepository,
        ScoutRepository scoutRepository,
        ParticipacionRepository participacionRepository
      
) {
    this.actividadRepository = actividadRepository;
    this.scoutRepository = scoutRepository;
    this.participacionRepository = participacionRepository;
    
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

public List<MisActividadesDto> misActividades() {

    String username = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    Scout scout = scoutRepository.findByApodo(username)
            .orElseThrow();
    
    List<Participacion> participaciones =
            participacionRepository.findByScoutId(scout.getId());

    return participaciones.stream()
            .map(p -> new MisActividadesDto(
                    p.getActividad().getId(),
                    p.getActividad().getDescripcion(),
                    p.getActividad().getFecha(),
                    p.getObservaciones()
            ))
            .toList();
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
