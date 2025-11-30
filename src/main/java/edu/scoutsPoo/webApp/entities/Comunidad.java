package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    
    private String actividadPrincipal;

    @ManyToMany
    @JoinTable(
        name = "comunidad_sede",
        joinColumns = @JoinColumn(name = "comunidad_numero"),
        inverseJoinColumns = @JoinColumn(name = "sede_codigo")
    )
    private Set<Sede> sedes = new HashSet<>();

    // Constructors
    public Comunidad() {}

    public Comunidad(String actividadPrincipal) {
            this.actividadPrincipal = actividadPrincipal;
    }

    // Getters & Setters
    public Long getNumero() { return numero; }
    public String getActividadPrincipal() {return actividadPrincipal;}


         
    public void setActividadPrincipal(String actividadPrincipal) {
        this.actividadPrincipal = actividadPrincipal;
    }

    public Set<Sede> getSedes() { return sedes; }

    @Override
    public int hashCode() { return (numero == null) ? 0 : numero.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comunidad)) return false;
        Comunidad other = (Comunidad) o;
        return numero != null && numero.equals(other.numero);
    }

}
