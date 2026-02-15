package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.security.JwtUtil;
import edu.scoutsPoo.webApp.entities.Usuario;
import edu.scoutsPoo.webApp.services.UsuarioService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager,
                          UsuarioService usuarioService,
                          JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario data) {

        var authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    data.getUsername(),
                    data.getPassword()
            )
    );
var userDetails = (org.springframework.security.core.userdetails.UserDetails)
            authentication.getPrincipal();

    String token = jwtUtil.generarToken(userDetails);

    return ResponseEntity.ok().body(
            java.util.Map.of("token", token)
    );
}

}
