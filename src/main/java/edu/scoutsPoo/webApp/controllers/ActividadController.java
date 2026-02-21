package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Actividad;
import edu.scoutsPoo.webApp.entities.Participacion;
import edu.scoutsPoo.webApp.services.ActividadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.scoutsPoo.webApp.DTOs.ActividadDto;

@RestController
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    // --------------------------------------------------------------
    // GET ALL
    // --------------------------------------------------------------
    @GetMapping
    public List<Actividad> getAll() {
        return actividadService.findAll();
    }

    // --------------------------------------------------------------
    // GET BY ID
    // --------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Actividad> getById(@PathVariable Long id) {
        return actividadService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------------------------------------------------------------
    // GET MIS ACTIVIDADES
    // ---------------------------------------------------------------
    @GetMapping("/misActividades")
    public ResponseEntity<List<ActividadDto>> misActividades() {
        return ResponseEntity.ok(actividadService.misActividades());
}

    // --------------------------------------------------------------
    // CREATE
    // --------------------------------------------------------------
    @PostMapping
  public Actividad create(@RequestBody Actividad nueva) {
    return actividadService.create(nueva);
}



    // --------------------------------------------------------------
    // UPDATE

   @PutMapping("/{id}")
public ResponseEntity<Actividad> update(
        @PathVariable Long id,
        @RequestBody Map<String, String> body
) {
    Optional<Actividad> res = actividadService.findById(id);
    if (res.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Actividad existente = res.get();
    String descripcionNueva = body.get("descripcion");
    existente.setDescripcion(descripcionNueva);

    // Guardamos con el service
    actividadService.update(id ,descripcionNueva); // o update
    return ResponseEntity.ok(existente);
}

    // --------------------------------------------------------------
    // DELETE
    // --------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!actividadService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        actividadService.delete(id);
        return ResponseEntity.noContent().build();
    }


@GetMapping("/{id}/participaciones")
public ResponseEntity<List<Participacion>> participaciones(@PathVariable Long id) {

    Optional<Actividad> opt = actividadService.findById(id);

    if (opt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Actividad actividad = opt.get();

    List<Participacion> participaciones =
        new ArrayList<>(actividad.getParticipaciones());
        System.out.println(participaciones);

    return ResponseEntity.ok(participaciones);
}

} 
