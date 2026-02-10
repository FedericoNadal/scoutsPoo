package edu.scoutsPoo.webApp.services;

import edu.scoutsPoo.webApp.entities.*;
import edu.scoutsPoo.webApp.repositories.UsuarioRepository;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ScoutService scoutService;


    public UsuarioService(UsuarioRepository usuarioRepository,
                        BCryptPasswordEncoder passwordEncoder, 
                        ScoutService scoutService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.scoutService = scoutService;
    }

    // ---------------------------------------------------
    // REGISTRO
    // ---------------------------------------------------
    public Usuario registrar(String username, String password, Scout scout, Rol rolManual) {

    if (usuarioRepository.findByUsername(username).isPresent()) {
        throw new RuntimeException("El nombre de usuario ya existe");
    }

    // 🔍 Intentar resolver scout si viene null
    Scout scoutFinal = Optional.ofNullable(scout)
            .or(() -> scoutService.findByApodo(username))
            .orElse(null);

    Rol rolAsignado;

    if (scoutFinal != null) {
        rolAsignado = deducirRolDesdeScout(scoutFinal);
    } else {
        if (rolManual == null) {
            throw new RuntimeException(
                "Un usuario sin scout debe tener un rol explícito"
            );
        }
        rolAsignado = rolManual;
    }

    Usuario usuario = new Usuario(
            username,
            passwordEncoder.encode(password),
            rolAsignado,
            scoutFinal
    );

    return usuarioRepository.save(usuario);
}

    // ---------------------------------------------------
    // LOGIN
    // ---------------------------------------------------
    public Usuario login(String username, String password) {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user; // Aquí luego se reemplaza por JWT o sesión
    }

    // ---------------------------------------------------
    // LOGICA: deducir el rol según la graduación del scout.
    //
    // ---------------------------------------------------
    private Rol deducirRolDesdeScout(Scout scout) {
    Graduacion grad = scout.getGraduacion();

    switch (grad) {
        case CASTOR:
        case LOBATO:
        case LOBEZNA:
        case CAMINANTE:
            return Rol.SCOUT;
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

public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

 public void actualizarRolSegunScout(Scout scout) {
    if (scout == null) return;

    Optional<Usuario> optUsuario = usuarioRepository.findByScout(scout);
    if (optUsuario.isEmpty()) return;

    Usuario usuario = optUsuario.get();

    Rol nuevoRol = deducirRolDesdeScout(scout);

    if (usuario.getRol() != nuevoRol) {
        usuario.setRol(nuevoRol);
        usuarioRepository.save(usuario);
    }
}

}
