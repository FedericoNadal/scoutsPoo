package edu.scoutsPoo.webApp.controllers;

import java.util.List;
import java.util.Optional;

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


@RestController
@RequestMapping("/scouts")
public class ScoutController {

    //@Autowired
    
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
public ResponseEntity<Scout> getByApodo(@PathVariable String apodo) {
    return scoutService.findByApodo(apodo)
            .map(s -> ResponseEntity.ok(s))
            .orElse(ResponseEntity.notFound().build());
}

     // --------------------------------------------------------------
    // GET BY Id
    // --------------------------------------------------------------
    @GetMapping("/{id}") 
    public ResponseEntity<Scout> getById(@PathVariable Long id) {
    return scoutService.findById(id)
            .map(s -> ResponseEntity.ok(s))
            .orElse(ResponseEntity.notFound().build());
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
public ResponseEntity<Scout> update(@PathVariable Long id, @RequestBody Scout nuevo) {
    return scoutService.findById(id).isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(scoutService.update(id, nuevo));
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

    //hard delete no se usa desde front.
    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDelete(@PathVariable Long id) {
        if (!scoutService.existsById(id)) {
        return ResponseEntity.notFound().build();
    }
        scoutService.hardDelete(id);
        return ResponseEntity.noContent().build();
    }


}
