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
    /**
     * Identificador único de la orden.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrden")
    private Long idOrden;
    /**
     * Cantidad de unidades de comida en la orden.
     * Este campo es obligatorio.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    /**
     * Subtotal de la orden, calculado en base a la cantidad y el precio de la comida.
     * Este campo es obligatorio.
     */
    @Column(name = "subTotal", nullable = false)
    private double subTotal;
    /**
     * Relación con la comida que se está ordenando.
     * Este campo es obligatorio.
     */
    @ManyToOne
    @JoinColumn(name = "idComida", nullable = false)
    private Comida comida;
    /**
     * Relación con la mesa donde se realiza la orden.
     * Este campo es obligatorio.
     */
    @ManyToOne
    @JoinColumn(name = "idMesa", nullable = false)
    private Mesa mesa;
    /**
     * Constructor vacío para la entidad Orden.
     * Utilizado por el framework para la creación de instancias.
     */
    public Orden() {
    }
    /**
     * Constructor con parámetros para crear una instancia de Orden.
     *
     * @param idOrden el identificador único de la orden
     * @param cantidad la cantidad de comida en la orden
     * @param subTotal el subtotal de la orden
     * @param comida el tipo de comida que se está ordenando
     * @param mesa la mesa donde se realiza la orden
     */
    public Orden(Long idOrden, int cantidad, double subTotal, Comida comida, Mesa mesa) {
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.comida = comida;
        this.mesa = mesa;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único de la orden.
     *
     * @return el id de la orden
     */
    public Long getIdOrden() {
        return idOrden;
    }
    /**
     * Establece el identificador único de la orden.
     *
     * @param idOrden el nuevo id de la orden
     */
    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }
    /**
     * Obtiene la cantidad de comida en la orden.
     *
     * @return la cantidad de comida
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * Establece la cantidad de comida en la orden.
     *
     * @param cantidad la nueva cantidad de comida
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    /**
     * Obtiene el subtotal de la orden.
     *
     * @return el subtotal de la orden
     */
    public double getSubTotal() {
        return subTotal;
    }
    /**
     * Establece el subtotal de la orden.
     *
     * @param subTotal el nuevo subtotal de la orden
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    /**
     * Obtiene la comida asociada a la orden.
     *
     * @return el tipo de comida que se está ordenando
     */
    public Comida getComida() {
        return comida;
    }
    /**
     * Establece el tipo de comida que se está ordenando.
     *
     * @param comida el nuevo tipo de comida
     */
    public void setComida(Comida comida) {
        this.comida = comida;
    }
    /**
     * Obtiene la mesa asociada a la orden.
     *
     * @return la mesa donde se realiza la orden
     */
    public Mesa getMesa() {
        return mesa;
    }
    /**
     * Establece la mesa donde se realiza la orden.
     *
     * @param mesa la nueva mesa asociada
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
