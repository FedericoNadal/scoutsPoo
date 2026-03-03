package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Comunidad;
import edu.scoutsPoo.webApp.services.ComunidadService;
import edu.scoutsPoo.webApp.services.SedeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comunidades")
public class ComunidadController {

    private final ComunidadService comunidadService;
    private final SedeService sedeService;

    public ComunidadController(ComunidadService comunidadService, SedeService sedeService) {
        this.comunidadService = comunidadService;
        this.sedeService = sedeService;
    }

    // ----------------------------------------
    // CREATE
    // ----------------------------------------
    @PostMapping
    public Comunidad create(@RequestBody Comunidad comunidad) {
        return comunidadService.save(comunidad);
    }

    // ----------------------------------------
    // READ ALL
    // ----------------------------------------
    @GetMapping
    public List<Comunidad> getAll() {
        return comunidadService.findAll();
    }

    // ----------------------------------------
    // READ BY ID
    // ----------------------------------------
    @GetMapping("/{numero}")
    public Comunidad getById(@PathVariable Long numero) {
        return comunidadService.findByNumero(numero)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comunidad no encontrada"));
    }

    // ----------------------------------------
    // UPDATE (solo atributos editables)
    // ----------------------------------------
    @PutMapping("/{numero}")
    public Comunidad update(@PathVariable Long numero, @RequestBody Comunidad nueva) {
        Comunidad existente = comunidadService.findByNumero(numero)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comunidad no encontrada"));

        // Solo se actualizan los campos existentes según la consigna
        existente.setActividadPrincipal(nueva.getActividadPrincipal());

        return comunidadService.save(existente);
    }

    // ----------------------------------------
    // DELETE
    // ----------------------------------------
   @DeleteMapping("/{numero}")
    public ResponseEntity<Void> delete(@PathVariable Long numero) {
     if (comunidadService.findByNumero(numero).isEmpty()) {
        return ResponseEntity.notFound().build();
        }
        comunidadService.deleteByNumero(numero);
        return ResponseEntity.noContent().build(); 
    }

   
}
