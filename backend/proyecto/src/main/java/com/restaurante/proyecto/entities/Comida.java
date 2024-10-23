package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comida")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComida")
    private Long idComida;

    @Column(name = "nombreComida", nullable = false, length = 50)
    private String nombreComida;

    @Column(name = "tipoComida", nullable = false, length = 50)
    private String tipoComida;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    public Comida() {
    }

    public Comida(Long idComida, String nombreComida, String tipoComida, String descripcion, double precio) {
        this.idComida = idComida;
        this.nombreComida = nombreComida;
        this.tipoComida = tipoComida;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Long getIdComida() {
        return idComida;
    }

    public void setIdComida(Long idComida) {
        this.idComida = idComida;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
