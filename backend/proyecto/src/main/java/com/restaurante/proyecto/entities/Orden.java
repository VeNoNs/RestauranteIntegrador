package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

/**
 * Clase que representa una orden en el sistema de gestión de restaurantes.
 *
 * Una orden contiene información sobre la cantidad de comida, el subtotal,
 * y está asociada a un tipo de comida y a una mesa específica.
 */
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

    /**
     * Estado actual de la orden, puede ser "pendiente", "en proceso", "terminado", etc.
     * Este campo es obligatorio.
     */
    @Column(name = "estado", nullable = false)
    private String estado;

    // Constructor vacío
    public Orden() {
    }

    // Constructor con parámetros
    public Orden(Long idOrden, int cantidad, double subTotal, Comida comida, Mesa mesa, String estado) {
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.comida = comida;
        this.mesa = mesa;
        this.estado = estado;
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

    /**
     * Obtiene el estado actual de la orden.
     *
     * @return el estado de la orden (por ejemplo, "pendiente", "en proceso", "terminado")
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la orden.
     *
     * @param estado el nuevo estado de la orden
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
