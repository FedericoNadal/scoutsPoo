
package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.DTOs.ScoutDto;
import edu.scoutsPoo.webApp.entities.Comunidad;
import edu.scoutsPoo.webApp.entities.Graduacion;
import edu.scoutsPoo.webApp.entities.Grupo;
import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.entities.Sede;
import edu.scoutsPoo.webApp.repositories.ScoutRepository;
import java.util.List;
import java.util.Optional;
//import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fede
 */

@Service
public class ScoutService {

    @Autowired
    private final ScoutRepository scoutRepository;
    private final SedeService sedeService;
    private final GrupoService grupoService;
    private final ComunidadService comunidadService;

    public ScoutService(
            ScoutRepository scoutRepository,
            SedeService sedeService,
            GrupoService grupoService,
            ComunidadService comunidadService
    ) {
        this.scoutRepository = scoutRepository;
        this.sedeService = sedeService;
        this.grupoService = grupoService;
        this.comunidadService = comunidadService;
    }

    public Scout createFromDto(ScoutDto dto) {
        if (dto.getIdSede() == null) {
                
    throw new IllegalArgumentException("Id de Sede no puede ser null");
}
        Sede sede = sedeService.findByCodigo(dto.getIdSede());
                //.orElseThrow(() -> new RuntimeException("Sede no encontrada: " + dto.getIdSede()));

        Grupo grupo = grupoService.findById(dto.getIdGrupo())
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado: " + dto.getIdGrupo()));

        Comunidad comunidad = comunidadService.findByNumero(dto.getIdComunidad())
                .orElseThrow(() -> new RuntimeException("Comunidad no encontrada: " + dto.getIdComunidad()));

        Graduacion grad = Graduacion.valueOf(dto.getGraduacion());

        Scout nuevoScout = new Scout(
                dto.getApodo(),
                dto.getNombre(),
                dto.getApellido(),
                grad,
                grupo,
                comunidad,
                sede
        );

        return scoutRepository.save(nuevoScout);
    }

    public List<Scout> findAll() {
        return scoutRepository.findAll();
    }

    public Scout findByApodo(String apodo) {
        return scoutRepository.findByApodo(apodo)
                .orElse(null);
    }

    public Scout save(Scout scout) {
        return scoutRepository.save(scout);
    }

    public void delete(Long id) {
        scoutRepository.findById(id)
                .ifPresent(scoutRepository::delete);
    }

    public boolean existsByApodo(String apodo) {
    return scoutRepository.existsByApodo(apodo);
    }

    public Scout create(Scout nuevoScout) {
        return scoutRepository.save(nuevoScout);
    
    }

    public Optional <Scout> findById(Long id) {
        return scoutRepository.findById(id);
    
    }

    public boolean existsById(Long id) {
       return scoutRepository.existsById(id);
    
    }
}

