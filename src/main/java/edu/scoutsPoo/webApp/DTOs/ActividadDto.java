/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.scoutsPoo.webApp.DTOs;

//import java.util.Date;
import java.time.LocalDate;

import edu.scoutsPoo.webApp.entities.Actividad;

/**
 *
 * @author fede
 */
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


