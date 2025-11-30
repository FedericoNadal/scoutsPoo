
package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.repositories.ScoutRepository;
import java.util.List;
import java.util.Optional;
//import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fede
 */

@Service
public class ScoutService {

    @Autowired
    private ScoutRepository scoutRepository;

    public List<Scout> findAll() {
        return scoutRepository.findAll();
    }

    public Scout findByApodo(String apodo) {
        return scoutRepository.findByApodo(apodo)
                .orElse(null);
    }

    public Scout save(Scout scout) {
        return scoutRepository.save(scout);
    }

    public void delete(String apodo) {
        scoutRepository.findByApodo(apodo)
                .ifPresent(scoutRepository::delete);
    }

    public boolean existsByApodo(String apodo) {
    return scoutRepository.existsByApodo(apodo);
    }
}

