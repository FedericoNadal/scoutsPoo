package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Sede;
import edu.scoutsPoo.webApp.services.SedeService;
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
    public Sede getByCodigo(@PathVariable Long codigo) {
        return sedeService.findByCodigo(codigo);
    }

    @PostMapping
    public Sede create(@RequestBody Sede sede) {
        return sedeService.create(sede);
    }

   @PutMapping("/{codigo}")//!"OJO, probar si funciona la sobreesritura"
public Sede update(@PathVariable Long codigo, @RequestBody Sede nueva) {
    Sede existente = sedeService.findByCodigo(codigo);

    existente.setNombre(nueva.getNombre());
    existente.setDireccion(nueva.getDireccion());
    existente.setProvincia(nueva.getProvincia());
    existente.setLocalidad(nueva.getLocalidad());

    return sedeService.update(codigo, existente);
} 

    @DeleteMapping("/{codigo}")
    public void delete(@PathVariable Long codigo) {
        sedeService.delete(codigo);
    }
}
