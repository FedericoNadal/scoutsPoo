package edu.scoutsPoo.webApp.controllers;

import edu.scoutsPoo.webApp.entities.Rol;
import edu.scoutsPoo.webApp.entities.Scout;
import edu.scoutsPoo.webApp.entities.Usuario;
import edu.scoutsPoo.webApp.services.ScoutService;
import edu.scoutsPoo.webApp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ScoutService scoutService;

    // ---------------------------------------------------
    // REGISTRO DE USUARIO
    // ---------------------------------------------------
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Map<String, String> payload) {
        try {
            String username = payload.get("username");
            String password = payload.get("password");
            String rolStr = payload.get("rol");         // opcional

            Scout scout = null;
        
            Rol rolManual = null;
            if (rolStr != null) {
                rolManual = Rol.valueOf(rolStr.toUpperCase());
            }

            Usuario usuario = usuarioService.registrar(username, password, scout, rolManual);
            return ResponseEntity.ok(usuario);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ---------------------------------------------------
    // LOGIN DE USUARIO
    // ---------------------------------------------------
    @PostMapping("/login-viejo") //OJO!! solo para testing de security!!
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        try {
            String username = payload.get("username");
            String password = payload.get("password");
            Usuario usuario = usuarioService.login(username, password);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
