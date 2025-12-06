package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;
//import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Participacion> participaciones = new HashSet<>();

    // Constructor vacío requerido por JPA
    public Actividad() {}

    public Actividad(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

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





/* 

package edu.scoutsPoo.webApp.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
*/
 
/**
 * Clase actvidad: 
 * las actividades constituyen la piedra angular del sistema de gestion.
 * presentan un codigo,una descripcion y una fecha, segun requerimiestos
 * a las actividades se anotan una lista de participantes (sub-clase de scouts)
 * 
 */
/* public class Actividad {
   
    private final int codigo;
    private String descripcion;
    private Date fecha;
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<String> apodosScouts = new ArrayList<>();
    private final SimpleDateFormat fechaConFormato = new SimpleDateFormat("dd-MM-yyyy");

    // Constructor
    public Actividad(int codigo, String descripcion, Date fecha) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.participantes = new ArrayList<>();
    }

    public void agregarApodoScout(String apodo) {
        apodosScouts.add(apodo);
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getFechaConFormato() {
        return fechaConFormato.format(fecha);
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }*/


    /**
     *  Método para obtener un participante específico por scout
     * @param scout
     * @return participante
     */
    
/*  public Participante getParticipante(Scout scout) {
        for (Participante participante : participantes) {
            if (participante.getScout().equals(scout)) {
                return participante;
            }
        }
        return null; 
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }*/
   
/**
 *  Métodos para agregar participantes, sin ninguna o con multiples responsabilidades
 *  @param scout 
 */
    /* public void agregarParticipante(Scout scout) {
        Participante p = new Participante(scout);
        participantes.add(p);
    }
    
    public void agregarParticipante(Scout scout, String responsabilidad) {
        Participante p = new Participante(scout);
        p.asignarResponsabilidad(responsabilidad);
        participantes.add(p);
    }

    public void agregarParticipante(Scout scout, String resp1, String resp2) {
        Participante p = new Participante(scout);
        p.asignarResponsabilidad(resp1);
        p.asignarResponsabilidad(resp2);
        participantes.add(p);
    }

    public void agregarParticipante(Scout scout, String resp1, String resp2, String resp3) {
        Participante p = new Participante(scout);
        p.asignarResponsabilidad(resp1);
        p.asignarResponsabilidad(resp2);
        p.asignarResponsabilidad(resp3);
        participantes.add(p);
    }

    public boolean esParticipante(Scout s) {
        return participantes.contains((Participante) s); 
    }
}*/
    
   
