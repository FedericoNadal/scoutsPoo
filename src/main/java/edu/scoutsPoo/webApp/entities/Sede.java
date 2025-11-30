/**
 * La clase sede presenta, segun requerimientos, nombre direccion y ubicacion. En
 * versiones iniciales del sistema se propuso registrar esta ultima en
 * coordenada geodesicas [int,int] pero dadas la propuesta de vista por consola,
 * resultaba mas claro utilizar direccion, provincia, y localidad.
 * Implementarlo con mapa para la version WebApp no se descarta, pero tampoco sera prioritario
 *
 */

package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nombre;
    private String direccion;
    private String provincia;
    private String localidad;

    @ManyToMany(mappedBy = "sedes")
    private Set<Comunidad> comunidades = new HashSet<>();

    // Constructors
    public Sede() {}

    public Sede(String nombre, String direccion, String provincia, String localidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    // Getters & Setters
    //public Long getId() { return id; }

    public Long getCodigo() { return codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public Set<Comunidad> getComunidades() { return comunidades; }

    // equals/hashCode solo por id
    @Override
    public int hashCode() { return (codigo == null) ? 0 : codigo.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sede)) return false;
        Sede other = (Sede) o;
        return codigo != null && codigo.equals(other.codigo);
    }
}
