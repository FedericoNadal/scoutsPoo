package edu.scoutsPoo.webApp.DTOs;


import edu.scoutsPoo.webApp.entities.Actividad;
import java.time.LocalDate;

public class ActividadDto {

    private Long id;
    private String descripcion;
    private LocalDate fecha;
    

    public ActividadDto() {
    }

    public static ActividadDto fromEntity(Actividad actividad) {
        ActividadDto dto = new ActividadDto();
        dto.setId(actividad.getId());
        dto.setDescripcion(actividad.getDescripcion());
        dto.setFechaActividad(actividad.getFecha());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionActividad() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaActividad() {
        return fecha;
    }

    public void setFechaActividad(LocalDate fecha) {
        this.fecha = fecha;
    }
}


