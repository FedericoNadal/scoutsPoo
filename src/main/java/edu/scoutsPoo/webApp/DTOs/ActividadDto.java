/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.scoutsPoo.webApp.DTOs;

//import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author fede
 */
public class ActividadDto {
    
     
    private String descripcion;
    private LocalDate fecha; 

    
      
       public String getDescripcionActividad() {
        return descripcion;
    }
    
     public LocalDate getFechaActividad() {
        return fecha;
    }
      
  
      
///////////////////////////////////////////

   
     public void setFechaActividad(LocalDate fechaActividad) {
        this.fecha = fechaActividad;
    }
}

