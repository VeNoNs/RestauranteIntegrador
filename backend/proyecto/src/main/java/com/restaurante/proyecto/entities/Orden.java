package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrden")
    private Long idOrden;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "subTotal", nullable = false)
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "idComida", nullable = false)
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "idMesa", nullable = false)
    private Mesa mesa;

    public Orden() {
    }

    public Orden(Long idOrden, int cantidad, double subTotal, Comida comida, Mesa mesa) {
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.comida = comida;
        this.mesa = mesa;
    }

    // Getters y Setters
    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
