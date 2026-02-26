package edu.scoutsPoo.webApp.DTOs;

import java.time.LocalDate;

public class ParticipacionDetalleDto {

    private Long participacionId;

    // Datos del Scout
    private Long scoutId;
    private String scoutNombre;
    private String scoutApellido;
    private String scoutApodo;
    private String scoutSede;
    private String scoutGrupo;
    private String scoutComunidad;


    // Datos de la Actividad
    private Long actividadId;
    private String actividadDescripcion;
    private LocalDate actividadFecha;

    // Participación
    private String observaciones;

    public ParticipacionDetalleDto() {
    }

    public Long getParticipacionId() {
        return participacionId;
    }

    public void setParticipacionId(Long participacionId) {
        this.participacionId = participacionId;
    }

    public Long getScoutId() {
        return scoutId;
    }

    public void setScoutId(Long scoutId) {
        this.scoutId = scoutId;
    }

    public String getScoutNombre() {
        return scoutNombre;
    }

    public void setScoutNombre(String scoutNombre) {
        this.scoutNombre = scoutNombre;
    }

    public String getScoutApodo() {
        return scoutApodo;
    }

    public void setScoutApodo(String scoutApodo) {
        this.scoutApodo = scoutApodo;
    }

    public String getScoutApellido() {
        return scoutApellido;
    }

    public void setScoutApellido(String scoutApellido) {
        this.scoutApellido = scoutApellido;
    }

    public String getScoutSede() {
        return scoutSede;
    }

    public void setScoutSede(String scoutSede) {
        this.scoutSede = scoutSede;
    }

      public String getScoutGrupo() {
        return scoutGrupo;
    }

    public void setScoutGrupo(String scoutGrupo) {
        this.scoutGrupo = scoutGrupo;
    }

    public String getScoutComunidad() {
        return scoutComunidad;
    }

    public void setScoutComunidad(String scoutComunidad) {
        this.scoutComunidad = scoutComunidad;
    }

    //////////////////////////////////////////

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public String getActividadDescripcion() {
        return actividadDescripcion;
    }

    public void setActividadDescripcion(String actividadNombre) {
        this.actividadDescripcion = actividadNombre;
    }

    public LocalDate getActividadFecha() {
        return actividadFecha;
    }

    public void setActividadFecha(LocalDate actividadFecha) {
        this.actividadFecha = actividadFecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}