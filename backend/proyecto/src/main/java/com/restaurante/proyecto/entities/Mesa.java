package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nroMesa")
    private Long nroMesa;

    @Column(name = "cantidadSillas", nullable = false)
    private int cantidadSillas;

    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    public Mesa() {
    }

    public Mesa(Long nroMesa, int cantidadSillas, String estado) {
        this.nroMesa = nroMesa;
        this.cantidadSillas = cantidadSillas;
        this.estado = estado;
    }

    public Long getNroMesa() {
        return nroMesa;
    }

    public void setNroMesa(Long nroMesa) {
        this.nroMesa = nroMesa;
    }

    public int getCantidadSillas() {
        return cantidadSillas;
    }

    public void setCantidadSillas(int cantidadSillas) {
        this.cantidadSillas = cantidadSillas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}