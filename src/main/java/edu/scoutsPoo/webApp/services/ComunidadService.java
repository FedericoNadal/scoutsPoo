package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Comunidad;
import edu.scoutsPoo.webApp.repositories.ComunidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComunidadService {

    private final ComunidadRepository comunidadRepository;

    public ComunidadService(ComunidadRepository comunidadRepository) {
        this.comunidadRepository = comunidadRepository;
    }

    // Crear o actualizar
    public Comunidad save(Comunidad comunidad) {
        return comunidadRepository.save(comunidad);
    }

    // Obtener todas
    public List<Comunidad> findAll() {
        return comunidadRepository.findAll();
    }

    // Buscar por número (PK)
    public Optional<Comunidad> findByNumero(Long numero) {
        return comunidadRepository.findByNumero(numero);
    }

  

    // Buscar por actividad principal
    public List<Comunidad> findByActividadPrincipal(String actividadPrincipal) {
        return comunidadRepository.findByActividadPrincipal(actividadPrincipal);
    }

    // Eliminar
    public void deleteByNumero(Long numero) {
        comunidadRepository.deleteById(numero);
    }
}
