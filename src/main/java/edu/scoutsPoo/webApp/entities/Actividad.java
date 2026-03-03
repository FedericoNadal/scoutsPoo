/**
 * Clase actvidad: 
 * las actividades constituyen la piedra angular del sistema de gestion.
 * presentan un codigo,una descripcion y una fecha, segun requerimiestos
 * a las actividades se anotan una lista de participantes 
 * 
 */

package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.scoutsPoo.webApp.DTOs.ActividadDto;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private LocalDate fecha;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Participacion> participaciones = new HashSet<>();

    // Constructor vacío requerido por JPA
    public Actividad() {}

    public Actividad(ActividadDto nueva) {
        this.descripcion = nueva.getDescripcionActividad();
        this.fecha = nueva.getFechaActividad();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate f) { this.fecha = f; }

    public Set<Participacion> getParticipaciones() { return participaciones; }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actividad)) return false;
        Actividad other = (Actividad) o;
        return id != null && id.equals(other.id);
    }
}





