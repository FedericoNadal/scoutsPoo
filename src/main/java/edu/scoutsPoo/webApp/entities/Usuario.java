package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // hashed

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "scout_id")
    private Scout scout;   // puede ser null (admin)

    public Usuario() {}

    public Usuario(String username, String password, Rol rol, Scout scout) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.scout = scout;
    }

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Rol getRol() { return rol; }

    public Scout getScout() { return scout; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRol(Rol rol) { this.rol = rol; }
    public void setScout(Scout scout) { this.scout = scout; }


}
