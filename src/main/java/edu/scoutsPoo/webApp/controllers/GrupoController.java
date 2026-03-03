package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Grupo;
import edu.scoutsPoo.webApp.services.GrupoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
   public ResponseEntity<Grupo> getById(@PathVariable Long id) {
    return grupoService.findById(id)
            .map(g -> ResponseEntity.ok(g))
            .orElse(ResponseEntity.notFound().build());
}

    // CREATE
    @PostMapping
  public ResponseEntity<Grupo> create(@RequestBody Grupo grupo) {
    return ResponseEntity.ok(grupoService.create(grupo));
}

    // UPDATE  
    @PutMapping("/{id}")
public ResponseEntity<Grupo> update(@PathVariable Long id, @RequestBody Map<String, String> body) {
    return grupoService.findById(id).isEmpty() 
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(grupoService.update(id, body.get("denominacion")));
}

    // DELETE
    @DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (grupoService.findById(id).isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    grupoService.deleteById(id);
    return ResponseEntity.noContent().build(); 
}


} 