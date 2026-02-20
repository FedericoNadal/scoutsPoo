package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Participacion;
import edu.scoutsPoo.webApp.services.ParticipacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.scoutsPoo.webApp.DTOs.ParticipacionDto;

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
    public Participacion create(@RequestBody ParticipacionDto dto) {
        return participacionService.saveFromDto(dto);
    }

    // --------------------------------------------------------------
    // UPDATE
    // --------------------------------------------------------------
    @PutMapping("/{id}")
   public ResponseEntity<Participacion> updateObservaciones(
        @PathVariable Long id,
        @RequestBody Map<String, String> body) {

    return participacionService.findById(id)
        .map(p -> {
            if (body.containsKey("observaciones")) {
                p.setObservaciones(body.get("observaciones"));
            }
            return ResponseEntity.ok(participacionService.updateObservaciones(p));
        })
        .orElse(ResponseEntity.notFound().build());
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
