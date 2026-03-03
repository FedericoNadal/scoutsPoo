package edu.scoutsPoo.webApp.DTOs;



public class ParticipacionDto {

    private Long scoutId;
    private Long actividadId;
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


    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
