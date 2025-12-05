package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Grupo;
import edu.scoutsPoo.webApp.services.GrupoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    // GET all
    @GetMapping
    public List<Grupo> getAll() {
        return grupoService.findAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public Optional<Grupo> getById(@PathVariable Long id) {
        return grupoService.findById(id);
    }

    // CREATE
    @PostMapping
    public Grupo create(@RequestBody Grupo grupo) {
        return grupoService.save(grupo);
    }

    // UPDATE
    @PutMapping("/{id}")
public Grupo update(@PathVariable Long id, @RequestBody Map<String, String> body) {
    Grupo existente = grupoService.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe"));

    // Evitamos null y asignamos la denominación correctamente
    String nuevaDescripcion = body.get("descripcion");
    if (nuevaDescripcion != null) {
        existente.setDenominacion(nuevaDescripcion);
    }

    return grupoService.save(existente);
}

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        grupoService.deleteById(id);
    }
} 