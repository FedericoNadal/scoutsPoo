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
    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    // Obtener todos
    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Grupo> findById(Long id) {
        return grupoRepository.findById(id);
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
