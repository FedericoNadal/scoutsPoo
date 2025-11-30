package edu.scoutsPoo.webApp.DTOs;

import edu.scoutsPoo.webApp.entities.Rol;

public class UsuarioDTO {

    private String username;
    private Rol rol;
    private String scoutNombre; // opcional: nombre del scout asociado
    private String scoutApodo;  // opcional: apodo del scout

    public UsuarioDTO(String username, Rol rol) {
        this.username = username;
        this.rol = rol;
    }

    public UsuarioDTO(String username, Rol rol, String scoutNombre, String scoutApodo) {
        this.username = username;
        this.rol = rol;
        this.scoutNombre = scoutNombre;
        this.scoutApodo = scoutApodo;
    }

    // Getters y setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getScoutNombre() { return scoutNombre; }
    public void setScoutNombre(String scoutNombre) { this.scoutNombre = scoutNombre; }

    public String getScoutApodo() { return scoutApodo; }
    public void setScoutApodo(String scoutApodo) { this.scoutApodo = scoutApodo; }
}
