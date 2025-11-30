/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.scoutsPoo.webApp.DTOs;

/**
 *
 * @author fede
 */
public class SedeDto {

     
    private int codigo;
    private String nombre;
    private String direccion;
    private String provincia;
    private String localidad;

    
////////////////////////
//copnstructor
/////////////////////

     public SedeDto(int codigo, String nombre, String direccion, String provincia, String localidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public SedeDto() {} // ← opcional, pero útil para deserializar JSON
/////////////////////////////

    public int getCodigo() {
        return codigo;
    }
    public String getNombreSede() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    /////////////////////////////////////////////////

    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }



}
