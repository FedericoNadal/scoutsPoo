package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Comunidad;
import edu.scoutsPoo.webApp.entities.Graduacion;
import edu.scoutsPoo.webApp.entities.Grupo;
import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.entities.Sede;
import edu.scoutsPoo.webApp.services.ScoutService;
//import edu.scoutsPoo.webApp.services.SedeService;
//import edu.scoutsPoo.webApp.services.GrupoService;
//import edu.scoutsPoo.webApp.services.ComunidadService;

import edu.scoutsPoo.webApp.DTOs.ScoutDto;

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
    
     private final ScoutService scoutService;

    public ScoutController(ScoutService scoutService) {
        this.scoutService = scoutService;
    }

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
    @GetMapping("/apodo/{apodo}")
    public Scout getByApodo(@PathVariable String apodo) {
        return scoutService.findByApodo(apodo);
               
    }

     // --------------------------------------------------------------
    // GET BY Id
    // --------------------------------------------------------------
    @GetMapping("/{id}")
public ResponseEntity<?> getById(@PathVariable Long id) {
    Optional<Scout> optional = scoutService.findById(id);

    if (optional.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(optional.get());
}


    // --------------------------------------------------------------
    // CREATE
    // --------------------------------------------------------------
 @PostMapping
    public Scout create(@RequestBody ScoutDto dto) {
        return scoutService.createFromDto(dto);
    }
 
    // --------------------------------------------------------------
    // UPDATE  (NO modificar id )
    // --------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Scout> update(@PathVariable Long id, @RequestBody Scout nuevo) {

        Optional<Scout> resultado = scoutService.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Scout existente = resultado.get();

        // No modificamos existente.setId(...)
      
       // existente.setApodo(nuevo.getApodo());
        existente.setNombre(nuevo.getNombre());
        existente.setApellido(nuevo.getApellido());
        existente.setGraduacion(nuevo.getGraduacion());

        //existente.setGrupo(nuevo.getGrupo());
        //existente.setComunidad(nuevo.getComunidad());
        //existente.setSede(nuevo.getSede());

        return ResponseEntity.ok(scoutService.save(existente));
    }

    // --------------------------------------------------------------
    // DELETE
    // --------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!scoutService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        scoutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
