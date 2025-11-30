package edu.scoutsPoo.webApp.entities;

import jakarta.persistence.*;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String denominacion;

    public Grupo() {}

    public Grupo(String denominacion) {
        this.denominacion = denominacion;
    }

    public Long getCodigo() { return codigo; }

    public String getDenominacion() { return denominacion; }
    public void setDenominacion(String denominacion) { this.denominacion = denominacion; }

    @Override
    public int hashCode() { return (codigo == null) ? 0 : codigo.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grupo)) return false;
        Grupo other = (Grupo) o;
        return codigo != null && codigo.equals(other.codigo);
    }
}
