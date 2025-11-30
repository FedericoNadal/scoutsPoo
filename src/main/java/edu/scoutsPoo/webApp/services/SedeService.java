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
               // .orElseThrow(() -> new IllegalArgumentException("La sede no existe"));
    }

    @Transactional
    public Sede create(Sede sede) {
        if (sedeRepo.existsByNombre(sede.getNombre())) {
            throw new IllegalArgumentException("Ya existe una sede con ese nombre");
        }
        return sedeRepo.save(sede);
    }

    @Transactional
    public Sede update(Long id, Sede nuevosDatos) {

        Sede sede = findByCodigo(id);

        sede.setNombre(nuevosDatos.getNombre());
        sede.setDireccion(nuevosDatos.getDireccion());
        sede.setProvincia(nuevosDatos.getProvincia());
        sede.setLocalidad(nuevosDatos.getLocalidad());

        return sedeRepo.save(sede);
    }

    @Transactional
    public void delete(Long codigo) {
        if (!sedeRepo.existsById(codigo)) {
            throw new IllegalArgumentException("La sede no existe");
        }
        sedeRepo.deleteById(codigo);
    }
}
