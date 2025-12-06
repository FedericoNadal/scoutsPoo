/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.scoutsPoo.webApp.DTOs;

import org.thymeleaf.expression.Ids;

//import edu.scoutsPoo.webApp.entities.Comunidad;
//import edu.scoutsPoo.webApp.entities.Grupo;
//import edu.scoutsPoo.webApp.entities.Sede;



/**
 *
 * @author fede
 */
public class ScoutDto {

    //private long codigo;
    private String apodo;
    private String nombre;
    private String apellido;
    private String graduacion;

    private Long idSede;         // solo el ID
    private Long idGrupo;        // solo el ID
    private Long idComunidad;    // solo el ID
    
   // private Grupo grupo; 
    //private Sede sede;
    //private Comunidad comunidad; 

    //public int getCodigo() {
       // return codigo;
   // }

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
/* 
public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
 public void setApodo(String apodo) {
        this.apodo = apodo;
    }
       public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

   

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }
    
*/

}
