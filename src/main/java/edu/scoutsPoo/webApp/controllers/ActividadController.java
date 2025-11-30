package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Actividad;
import edu.scoutsPoo.webApp.services.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // --------------------------------------------------------------
    // CREATE
    // --------------------------------------------------------------
    @PostMapping
    public Actividad create(@RequestBody String descripcionNueva) {
        return actividadService.create(descripcionNueva);
    }

    // --------------------------------------------------------------
    // UPDATE
    // --------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Actividad> update(
            @PathVariable Long id,
            @RequestBody String descripcionNueva
    ) {
        Optional<Actividad> res = actividadService.findById(id);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Actividad existente = res.get();
        return ResponseEntity.ok(actividadService.create(descripcionNueva));
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
}
