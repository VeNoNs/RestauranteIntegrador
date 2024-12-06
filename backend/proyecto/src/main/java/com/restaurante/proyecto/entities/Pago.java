package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
/**
 * La entidad {@code Pago} representa un pago realizado por un cliente en el sistema de gestión de un restaurante.
 * Contiene información sobre el monto del pago, la fecha en que se realizó y la relación con la orden correspondiente.
 */
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPago")
    private Long idPago; // Identificador único del pago

    @Column(name = "fechaPago", nullable = false)
    private LocalDate fechaPago; // Fecha en que se realizó el pago

    @Column(name = "monto", nullable = false)
    private double monto; // Monto del pago realizado

    @ManyToOne
    @JoinColumn(name = "idOrden", nullable = false)
    private Orden orden;  // Relación con la entidad Orden
    /**
     * Constructor por defecto para la clase {@code Pago}.
     */
    public Pago() {
    }
    /**
     * Constructor parametrizado para la clase {@code Pago}.
     *
     * @param idPago     el identificador del pago
     * @param fechaPago  la fecha en que se realizó el pago
     * @param monto      el monto del pago
     * @param orden      la orden relacionada con este pago
     */
    public Pago(Long idPago, LocalDate fechaPago, double monto, Orden orden) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.orden = orden;
    }
    /**
     * Obtiene el identificador del pago.
     *
     * @return el identificador del pago
     */
    public Long getIdPago() {
        return idPago;
    }
    /**
     * Establece el identificador del pago.
     *
     * @param idPago el nuevo identificador del pago
     */
    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }
    /**
     * Obtiene la fecha en que se realizó el pago.
     *
     * @return la fecha del pago
     */
    public LocalDate getFechaPago() {
        return fechaPago;
    }
    /**
     * Establece la fecha en que se realizó el pago.
     *
     * @param fechaPago la nueva fecha del pago
     */
    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
    /**
     * Obtiene el monto del pago.
     *
     * @return el monto del pago
     */
    public double getMonto() {
        return monto;
    }
    /**
     * Establece el monto del pago.
     *
     * @param monto el nuevo monto del pago
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }
    /**
     * Obtiene la orden relacionada con este pago.
     *
     * @return la orden relacionada
     */
    public Orden getOrden() {
        return orden;
    }
    /**
     * Establece la orden relacionada con este pago.
     *
     * @param orden la nueva orden relacionada
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
