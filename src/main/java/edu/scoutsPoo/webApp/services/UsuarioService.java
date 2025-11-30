package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.*;
import edu.scoutsPoo.webApp.repositories.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // ---------------------------------------------------
    // REGISTRO
    // ---------------------------------------------------
    public Usuario registrar(String username, String password, Scout scout, Rol rolManual) {

        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Rol rolAsignado;

        if (scout != null) {
            // Si tiene scout, deducimos rol automáticamente
            rolAsignado = deducirRolDesdeScout(scout);
        } else {
            // Si NO tiene scout → debe proveer rol explícito (ADMIN)
            if (rolManual == null) {
                throw new RuntimeException("Un usuario sin scout debe tener un rol explícito");
            }
            rolAsignado = rolManual;
        }

        Usuario usuario = new Usuario(
                username,
                encoder.encode(password),
                rolAsignado,
                scout
        );

        return usuarioRepository.save(usuario);
    }

    // ---------------------------------------------------
    // LOGIN
    // ---------------------------------------------------
    public Usuario login(String username, String password) {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user; // Aquí luego se reemplaza por JWT o sesión
    }

    // ---------------------------------------------------
    // LOGICA: deducir el rol según la graduación del scout
    // ---------------------------------------------------
    private Rol deducirRolDesdeScout(Scout scout) {
    Graduacion grad = scout.getGraduacion();

    switch (grad) {
        case CASTOR:
            return Rol.CASTOR;
        case LOBATO:
        case LOBEZNA:
            return Rol.LOBATO;
        case CAMINANTE:
            return Rol.CAMINANTE;
        case ROVER:
            return Rol.ROVER;
        case EDUCADOR:
            return Rol.EDUCADOR;
        default:
            throw new IllegalStateException("Graduación inválida: " + grad);
    }
}
@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRol().name()) // usa el enum Rol
                .build();
    }

}
