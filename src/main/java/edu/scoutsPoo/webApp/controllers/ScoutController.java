package edu.scoutsPoo.webApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.scoutsPoo.webApp.DTOs.ScoutDto;
import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.services.ScoutService;
import edu.scoutsPoo.webApp.services.UsuarioService;

@RestController
@RequestMapping("/scouts")
public class ScoutController {

    @Autowired
    
     private final ScoutService scoutService;
     private final UsuarioService usuarioService;

    public ScoutController(ScoutService scoutService, UsuarioService usuarioService) {
        this.scoutService = scoutService;
        this.usuarioService = usuarioService;
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
    public Optional<Scout> getByApodo(@PathVariable String apodo) {
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
    // UPDATE  (NO modificar id ni apodo )
    // --------------------------------------------------------------
    @PutMapping("/{id}")
public ResponseEntity<Scout> update(@PathVariable Long id,
                                    @RequestBody Scout nuevo) {

    Optional<Scout> resultado = scoutService.findById(id);
    if (resultado.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Scout existente = resultado.get();

    if (nuevo.getNombre() != null) {
        existente.setNombre(nuevo.getNombre());
    }

    if (nuevo.getApellido() != null) {
        existente.setApellido(nuevo.getApellido());
    }

    if (nuevo.getGraduacion() != null &&
        !nuevo.getGraduacion().equals(existente.getGraduacion())) {

        existente.setGraduacion(nuevo.getGraduacion());
       usuarioService.actualizarRolSegunScout(existente);
    }

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
