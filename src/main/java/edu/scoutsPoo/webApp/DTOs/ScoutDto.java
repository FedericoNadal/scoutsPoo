
package edu.scoutsPoo.webApp.DTOs;

public class ScoutDto {

   
    private String apodo;
    private String nombre;
    private String apellido;
    private String graduacion;

    private Long idSede;         // solo el ID
    private Long idGrupo;        // solo el ID
    private Long idComunidad;    // solo el ID
    
  
    public String getApodo() {
        return apodo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getGraduacion() {
        return graduacion;
    }
    
    public void setGraduacion(String graduacion) {
        this.graduacion = graduacion;
    }
    public Long getIdGrupo() {
        return idGrupo;
    }

    public Long getIdSede() {
  
        return idSede ;
        
    }
     public void setIdSede(Long IdSede) {
        this.idSede = IdSede;
    }

    public Long getIdComunidad() {
        return idComunidad;
    }


}
