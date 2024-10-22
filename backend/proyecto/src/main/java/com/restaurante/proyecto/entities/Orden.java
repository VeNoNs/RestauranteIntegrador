package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorden")
    private Long idOrden;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "subtotal", nullable = false)
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "idComida", nullable = false)
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private Local local;

    public Orden() {
    }

    public Orden(Long idOrden, int cantidad, double subTotal, Comida comida, Local local) {
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.comida = comida;
        this.local = local;
    }

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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
