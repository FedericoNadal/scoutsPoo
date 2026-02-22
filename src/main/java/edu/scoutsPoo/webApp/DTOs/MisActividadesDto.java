package edu.scoutsPoo.webApp.DTOs;

import java.time.LocalDate;

public class MisActividadesDto {

    private Long actividadId;
    private String descripcionActividad;
    private LocalDate fechaActividad;
    private String observaciones;

    public MisActividadesDto(Long actividadId,
                           String descripcionActividad,
                           LocalDate fechaActividad,
                           String observaciones) {
        this.actividadId = actividadId;
        this.descripcionActividad = descripcionActividad;
        this.fechaActividad = fechaActividad;
        this.observaciones = observaciones;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public LocalDate getFechaActividad() {
        return fechaActividad;
    }

    public String getObservaciones() {
        return observaciones;
    }
}