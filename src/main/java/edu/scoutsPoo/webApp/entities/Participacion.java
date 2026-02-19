/**
 * La clase particcio es una sub-clase de Scout que presenta tambien
 * resposabilidades y asistencia. Se instancian en una actividad determinada
 *
 */

package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;
//import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity

@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"actividad_id", "scout_id"})
    }
)

public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "scout_id", nullable = false)
    private Scout scout;

    @ManyToOne(optional = false)
    @JoinColumn(name = "actividad_id", nullable = false)
    @JsonBackReference
    private Actividad actividad;

    //private LocalDate fecha;

    private String observaciones;

    public Participacion() {}

    public Participacion(Scout scout, Actividad actividad, String obs) {
        this.scout = scout;
        this.actividad = actividad;
       // this.fecha = fecha;
        this.observaciones = obs;
    }

    public Long getId() { return id; }
    public Scout getScout() { return scout; }
    public void setScout(Scout s) { this.scout = s; }

    public Actividad getActividad() { return actividad; }
    public void setActividad(Actividad a) { this.actividad = a; }

   // public LocalDate getFecha() { return fecha; }
   // public void setFecha(LocalDate f) { this.fecha = f; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String obs) { this.observaciones = obs; }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participacion)) return false;
        Participacion other = (Participacion) o;
        return id != null && id.equals(other.id);
    }
}
