package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Sede;
import edu.scoutsPoo.webApp.repositories.SedeRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class SedeService {

    private final SedeRepository sedeRepo;

    public SedeService(SedeRepository sedeRepo) {
        this.sedeRepo = sedeRepo;
    }

    public List<Sede> findAll() {
        return sedeRepo.findAll();
    }
    
    public Sede findByCodigo(Long codigo) {
        return sedeRepo.findByCodigo(codigo);
     }

    @Transactional
    public Sede create(Sede sede) {
        if (sedeRepo.existsByNombre(sede.getNombre())) {
            throw new IllegalArgumentException("Ya existe una sede con ese nombre");
        }
        return sedeRepo.save(sede);
    }


    
   @Transactional
public Sede update(Long id, Sede nueva) {
    Sede existente = findByCodigo(id);
    
    if (existente == null) {
        throw new RuntimeException("Sede no encontrada: " + id);
    }
    
    if (nueva.getNombre() != null) {
        existente.setNombre(nueva.getNombre());
    }
    if (nueva.getDireccion() != null) {
        existente.setDireccion(nueva.getDireccion());
    }
    if (nueva.getProvincia() != null) {
        existente.setProvincia(nueva.getProvincia());
    }
    if (nueva.getLocalidad() != null) {
        existente.setLocalidad(nueva.getLocalidad());
    }
    
    return sedeRepo.save(existente);
}

    @Transactional
    public void delete(Long codigo) {
        if (!sedeRepo.existsById(codigo)) {
            throw new IllegalArgumentException("La sede no existe");
        }
        sedeRepo.deleteById(codigo);
    }
}
