package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.services.ScoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scouts")
public class ScoutController {

    @Autowired
    private ScoutService scoutService;

    // --------------------------------------------------------------
    // GET ALL
    // --------------------------------------------------------------
    @GetMapping
    public List<Scout> getAll() {
        return scoutService.findAll();
    }

    // --------------------------------------------------------------
    // GET BY APODO
    // --------------------------------------------------------------
    @GetMapping("/{apodo}")
    public Scout getByApodo(@PathVariable String apodo) {
        return scoutService.findByApodo(apodo);
               
    }

    // --------------------------------------------------------------
    // CREATE
    // --------------------------------------------------------------
    @PostMapping
    public Scout create(@RequestBody Scout nuevo) {
        return scoutService.save(nuevo);
    }

    // --------------------------------------------------------------
    // UPDATE  (NO modificar id )
    // --------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Scout> update(@PathVariable String apodo, @RequestBody Scout nuevo) {

        Optional<Scout> resultado = Optional.ofNullable(scoutService.findByApodo(apodo));
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Scout existente = resultado.get();

        // No modificamos existente.setId(...)
      
       // existente.setApodo(nuevo.getApodo());
        existente.setNombre(nuevo.getNombre());
        existente.setApellido(nuevo.getApellido());
        existente.setGraduacion(nuevo.getGraduacion());

        existente.setGrupo(nuevo.getGrupo());
        existente.setComunidad(nuevo.getComunidad());
        existente.setSede(nuevo.getSede());

        return ResponseEntity.ok(scoutService.save(existente));
    }

    // --------------------------------------------------------------
    // DELETE
    // --------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String apodo) {
        if (!scoutService.existsByApodo(apodo)) {
            return ResponseEntity.notFound().build();
        }
        scoutService.delete(apodo);
        return ResponseEntity.noContent().build();
    }
}
