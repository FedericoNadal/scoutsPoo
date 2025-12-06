package edu.scoutsPoo.webApp.DTOs;

import java.time.LocalDate;

public class ParticipacionDto {

    private Long scoutId;
    private Long actividadId;
    private LocalDate fecha;
    private String observaciones;

    public ParticipacionDto() {}

    public Long getScoutId() {
        return scoutId;
    }

    public void setScoutId(Long scoutId) {
        this.scoutId = scoutId;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
