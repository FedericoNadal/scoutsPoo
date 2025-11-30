/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.scoutsPoo.webApp.DTOs;

import java.util.Date;

/**
 *
 * @author fede
 */
public class ActividadDto {
    
    private int codigoActividad;
    private String nombreActividad;
    private String descripcionActividad;
    public  Date fechaActividad; 

    
      public String getNombreActividad() {
        return nombreActividad;
      }

       public String getDescripcionActividad() {
        return descripcionActividad;
    }
    
      public Date getFechaActividad() {
        return fechaActividad;
    }
      
      public int getCodigo(){
          return codigoActividad;
      }

      
///////////////////////////////////////////

       public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
}

