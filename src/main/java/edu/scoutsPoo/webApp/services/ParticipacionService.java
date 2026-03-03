package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Actividad;
import edu.scoutsPoo.webApp.entities.Participacion;
import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.repositories.ActividadRepository;
import edu.scoutsPoo.webApp.repositories.ParticipacionRepository;
import edu.scoutsPoo.webApp.repositories.ScoutRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.scoutsPoo.webApp.DTOs.ParticipacionDetalleDto;
import edu.scoutsPoo.webApp.DTOs.ParticipacionDto;

@Service
public class ParticipacionService {


    private final ParticipacionRepository participacionRepository;
    private final ScoutRepository scoutRepository;
    private final ActividadRepository actividadRepository;

    public ParticipacionService(ParticipacionRepository participacionRepository,
                                ScoutRepository scoutRepository,
                                ActividadRepository actividadRepository) {
        this.participacionRepository = participacionRepository;
        this.scoutRepository = scoutRepository;
        this.actividadRepository = actividadRepository;
    }



    // --------------------------------------------------------------
    // GET ALL
    // --------------------------------------------------------------
    public List<Participacion> findAll() {
        return participacionRepository.findAll();
    }

    // --------------------------------------------------------------
    // GET BY ID
    // --------------------------------------------------------------
    public Optional<Participacion> findById(Long id) {
        return participacionRepository.findById(id);
    }

    // --------------------------------------------------------------
    // GET BY SCOUT
    // --------------------------------------------------------------
    

public List<ParticipacionDetalleDto> findDetalleByScoutId(Long scoutId) {

    List<Participacion> participaciones =
            participacionRepository.findByScoutId(scoutId);

    return participaciones.stream()
            .map(p -> toDetalleDto(p))
            .toList();
}

private ParticipacionDetalleDto toDetalleDto(Participacion p) {

    ParticipacionDetalleDto dto = new ParticipacionDetalleDto();

    dto.setParticipacionId(p.getId());

    // Scout
    dto.setScoutId(p.getScout().getId());
    dto.setScoutNombre(p.getScout().getNombre());
    dto.setScoutApellido(p.getScout().getApellido());
    dto.setScoutApodo(p.getScout().getApodo());
    dto.setScoutSede(p.getScout().getSede().getNombre());
    dto.setScoutGrupo(p.getScout().getGrupo().getDenominacion());
    dto.setScoutComunidad(p.getScout().getComunidad().getActividadPrincipal());

    // Actividad
    dto.setActividadId(p.getActividad().getId());
    dto.setActividadDescripcion(p.getActividad().getDescripcion());
    dto.setActividadFecha(p.getActividad().getFecha());

    // Observaciones
    dto.setObservaciones(p.getObservaciones());

    return dto;
}



    // --------------------------------------------------------------
    // SAVE 
    // --------------------------------------------------------------
   private Participacion save(Participacion p) {

    if (p.getActividad() == null || p.getScout() == null) {
        throw new IllegalArgumentException("Actividad y Scout son obligatorios");
    }

    Long actividadId = p.getActividad().getId();
    Long scoutId = p.getScout().getId();

    if (participacionRepository
            .existsByScoutIdAndActividadId(scoutId,actividadId)) {

        throw new IllegalStateException(
            "El scout ya está inscripto en esta actividad");
    }

    return participacionRepository.save(p);
}

///
public Participacion saveFromDto(ParticipacionDto dto) {

    Scout scout = scoutRepository.findById(dto.getScoutId())
            .orElseThrow(() -> new IllegalArgumentException("Scout no encontrado"));

    Actividad actividad = actividadRepository.findById(dto.getActividadId())
            .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));

    if (participacionRepository
            .existsByScoutIdAndActividadId(scout.getId(), actividad.getId())) {

        throw new IllegalStateException(
                "El scout ya está inscripto en esta actividad");
    }

    Participacion participacion =
            new Participacion(scout, actividad, dto.getObservaciones());

    return participacionRepository.save(participacion);
}

////////////////////////
//UPDATE OBSERVACIONES
/////////////////////////

public Participacion updateObservaciones(Long id, String observaciones) {
    Participacion p = findById(id)
            .orElseThrow(() -> new RuntimeException("Participacion no encontrada: " + id));
    if (observaciones != null) {
        p.setObservaciones(observaciones);
    }
    return participacionRepository.save(p);
}

    
    // --------------------------------------------------------------
    // DELETE
    // --------------------------------------------------------------
    public void delete(Long id) {
        participacionRepository.deleteById(id);
    }

    // --------------------------------------------------------------
    // CHECK EXISTENCE
    // --------------------------------------------------------------
    public boolean existsById(Long id) {
        return participacionRepository.existsById(id);
    }
}
