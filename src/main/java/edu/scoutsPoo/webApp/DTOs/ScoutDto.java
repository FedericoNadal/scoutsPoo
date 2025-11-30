/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.scoutsPoo.webApp.DTOs;

import edu.scoutsPoo.webApp.entities.Comunidad;
import edu.scoutsPoo.webApp.entities.Grupo;
import edu.scoutsPoo.webApp.entities.Sede;

/**
 *
 * @author fede
 */
public class ScoutDto {

    private int codigo;
    private String apodo;
    private String nombre;
    private String apellido;
    private String graduacion;
    private Grupo grupo; 
    private Sede sede;
    private Comunidad comunidad; 

    public int getCodigo() {
        return codigo;
    }

    public String getApodo() {
        return apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGraduacion() {
        return graduacion;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Sede getSede() {
        return sede;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setGraduacion(String graduacion) {
        this.graduacion = graduacion;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }
    


}
