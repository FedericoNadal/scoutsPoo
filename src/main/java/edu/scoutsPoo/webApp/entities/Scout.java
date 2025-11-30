
/**
 * La clase scout es el la pieza central del sistema.
 * Presentan, segun requerimientos codigo, apodo, nombre, apellido y graduacion 
 * -Castor,Lobato y Lobezna, Scout, Caminante, Rover o Educador/a-
 * Tambien tienen, por conveniencia funcional del sistema e interpretación de los requerimientos, un grupo, una sede y una comunidad
 * si bien estas se instancian de forma independiente.
 * 
 * 
 */
package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;

@Entity
public class Scout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
     private String apodo;

    private String nombre;

    private String apellido;

    @Enumerated(EnumType.STRING)
    private Graduacion graduacion;

    @ManyToOne
    private Grupo grupo;

    @ManyToOne
    private Comunidad comunidad;

    @ManyToOne
    private Sede sede;

    public Scout() {}

    public Scout( String apodo, String nombre, String apellido,
                 Graduacion graduacion, Grupo grupo, Comunidad comunidad, Sede sede) {
        this.apodo = apodo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.graduacion = graduacion;
        this.grupo = grupo;
        this.comunidad = comunidad;
        this.sede = sede;
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

    public Graduacion getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(Graduacion graduacion) {
        this.graduacion = graduacion;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public String getApodo() {
        return apodo;
    }

    
}


    
    

