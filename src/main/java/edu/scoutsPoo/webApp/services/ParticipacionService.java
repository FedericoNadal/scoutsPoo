package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Actividad;
import edu.scoutsPoo.webApp.entities.Participacion;
import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.repositories.ActividadRepository;
import edu.scoutsPoo.webApp.repositories.ParticipacionRepository;
import edu.scoutsPoo.webApp.repositories.ScoutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Participacion saveFromDto(ParticipacionDto dto) {
        Scout scout = scoutRepository.findById(dto.getScoutId())
                .orElseThrow(() -> new IllegalArgumentException("Scout no encontrado"));

        Actividad actividad = actividadRepository.findById(dto.getActividadId())
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));

        Participacion participacion = new Participacion(scout, actividad, dto.getFecha(), dto.getObservaciones());

        return participacionRepository.save(participacion);
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
    // SAVE 
    // --------------------------------------------------------------
    public Participacion save(Participacion p) {
        return participacionRepository.save(p);
    }

    //---------------------------------
    //   UPDATE from dto
    //--------------------------------------
    public Participacion updateFromDto(Long id, ParticipacionDto dto) {
    Participacion p = participacionRepository.findById(id)
                     .orElseThrow(() -> new IllegalArgumentException("Participacion no encontrada"));

    Scout scout = scoutRepository.findById(dto.getScoutId())
                     .orElseThrow(() -> new IllegalArgumentException("Scout no encontrado"));
    Actividad actividad = actividadRepository.findById(dto.getActividadId())
                     .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));

    p.setScout(scout);
    p.setActividad(actividad);
    p.setFecha(dto.getFecha());
    p.setObservaciones(dto.getObservaciones());

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
