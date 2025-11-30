package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Participacion;
import edu.scoutsPoo.webApp.services.ParticipacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participaciones")
public class ParticipacionController {

    @Autowired
    private ParticipacionService participacionService;

    // --------------------------------------------------------------
    // GET ALL
    // --------------------------------------------------------------
    @GetMapping
    public List<Participacion> getAll() {
        return participacionService.findAll();
    }

    // --------------------------------------------------------------
    // GET BY ID
    // --------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Participacion> getById(@PathVariable Long id) {
        return participacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --------------------------------------------------------------
    // CREATE
    // --------------------------------------------------------------
    @PostMapping
    public Participacion create(@RequestBody Participacion nueva) {
        return participacionService.save(nueva);
    }

    // --------------------------------------------------------------
    // UPDATE
    // --------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Participacion> update(
            @PathVariable Long id,
            @RequestBody Participacion nueva
    ) {
        Optional<Participacion> res = participacionService.findById(id);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Participacion existente = res.get();

        // Actualizamos solo campos permitidos (ej. fecha, actividad, scout)
        existente.setFecha(nueva.getFecha());
        existente.setScout(nueva.getScout());
        existente.setActividad(nueva.getActividad());

        return ResponseEntity.ok(participacionService.save(existente));
    }

    // --------------------------------------------------------------
    // DELETE
    // --------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!participacionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        participacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
