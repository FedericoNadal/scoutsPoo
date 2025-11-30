package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Participacion;
import edu.scoutsPoo.webApp.repositories.ParticipacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipacionService {

    @Autowired
    private ParticipacionRepository participacionRepository;

    // --------------------------------------------------------------
    // GET ALL
    // --------------------------------------------------------------
    public List<Participacion> findAll() {
        return participacionRepository.findAll();
    }

    // --------------------------------------------------------------
    // GET BY ID
    // --------------------------------------------------------------
    public Optional<Participacion> findById(Long id) {
        return participacionRepository.findById(id);
    }

    // --------------------------------------------------------------
    // SAVE / CREATE / UPDATE
    // --------------------------------------------------------------
    public Participacion save(Participacion p) {
        return participacionRepository.save(p);
    }

    // --------------------------------------------------------------
    // DELETE
    // --------------------------------------------------------------
    public void delete(Long id) {
        participacionRepository.deleteById(id);
    }

    // --------------------------------------------------------------
    // CHECK EXISTENCE
    // --------------------------------------------------------------
    public boolean existsById(Long id) {
        return participacionRepository.existsById(id);
    }
}
