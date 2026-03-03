package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Grupo;
import edu.scoutsPoo.webApp.repositories.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    // Crear / guardar
    public Grupo create(Grupo grupo) {
        return grupoRepository.save(grupo);
    }
    
    //actualizar campos editables
    public Grupo update(Long id, String nuevaDenominacion) {
    Grupo existente = findById(id)
            .orElseThrow(() -> new RuntimeException("Grupo no encontrado: " + id));
    
    if (nuevaDenominacion != null) {
        existente.setDenominacion(nuevaDenominacion);
    }
    
    return grupoRepository.save(existente);
}
    // Obtener todos
    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Grupo> findById(Long id) {
        return grupoRepository.findByCodigo(id);
    }

    // Buscar por denominación
    public Optional<Grupo> findByDenominacion(String denominacion) {
        return grupoRepository.findByDenominacion(denominacion);
    }

    // Eliminar
    public void deleteById(Long codigo) {
        grupoRepository.deleteById(codigo);
    }

    public boolean existsByDenominacion(String denominacion) {
           return grupoRepository.existsByDenominacion(denominacion);
        }
}
