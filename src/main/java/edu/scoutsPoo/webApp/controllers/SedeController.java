package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Sede;
import edu.scoutsPoo.webApp.services.SedeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sedes")
public class SedeController {

    private final SedeService sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    public List<Sede> getAll() {
        return sedeService.findAll();
    }

 @GetMapping("/{codigo}")
    public ResponseEntity<Sede> getByCodigo(@PathVariable Long codigo) {
    Sede sede = sedeService.findByCodigo(codigo);
    return sede == null 
        ? ResponseEntity.notFound().build() 
        : ResponseEntity.ok(sede);
}

    @PostMapping
    public Sede create(@RequestBody Sede sede) {
        return sedeService.create(sede);
    }

  @PutMapping("/{codigo}")
public ResponseEntity<Sede> update(@PathVariable Long codigo, @RequestBody Sede nueva) {
    if (sedeService.findByCodigo(codigo) == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(sedeService.update(codigo, nueva));
}

  @DeleteMapping("/{codigo}")
public ResponseEntity<Void> delete(@PathVariable Long codigo) {
    if (sedeService.findByCodigo(codigo) == null) {
        return ResponseEntity.notFound().build();
    }
    sedeService.delete(codigo);
    return ResponseEntity.noContent().build();
}
}
