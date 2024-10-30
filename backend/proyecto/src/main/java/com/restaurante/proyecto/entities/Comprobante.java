package com.restaurante.proyecto.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
/**
 * Clase que representa un comprobante de pago en el sistema de gestión de un restaurante.
 *
 * Esta entidad almacena información relacionada con el comprobante generado
 * tras realizar un pago, incluyendo la fecha y la referencia al pago correspondiente.
 */
@Entity
@Table(name = "comprobante")
public class Comprobante {
    /**
     * Identificador único del comprobante.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComprobante")
    private Long idComprobante;
    /**
     * Fecha en la que se emite el comprobante.
     * Este campo es obligatorio.
     */
    @Column(name = "fechaComprobante", nullable = false)
    private LocalDate fechaComprobante;
    /**
     * Relación muchos a uno con la entidad Pago.
     * Indica el pago al que corresponde este comprobante.
     * Este campo es obligatorio.
     */
    @ManyToOne
    @JoinColumn(name = "idPago", nullable = false)
    private Pago pago;
    /**
     * Constructor vacío para la entidad Comprobante.
     * Utilizado por el framework para la creación de instancias.
     */
    public Comprobante() {
    }
    /**
     * Constructor con parámetros para crear una instancia de Comprobante.
     *
     * @param idComprobante el identificador único del comprobante
     * @param fechaComprobante la fecha en la que se emite el comprobante
     * @param pago el pago asociado al comprobante
     */
    public Comprobante(Long idComprobante, LocalDate fechaComprobante, Pago pago) {
        this.idComprobante = idComprobante;
        this.fechaComprobante = fechaComprobante;
        this.pago = pago;
    }
    /**
     * Obtiene el identificador único del comprobante.
     *
     * @return el id del comprobante
     */
    public Long getIdComprobante() {
        return idComprobante;
    }
    /**
     * Establece el identificador único del comprobante.
     *
     * @param idComprobante el nuevo id del comprobante
     */
    public void setIdComprobante(Long idComprobante) {
        this.idComprobante = idComprobante;
    }
    /**
     * Obtiene la fecha en la que se emite el comprobante.
     *
     * @return la fecha del comprobante
     */
    public LocalDate getFechaComprobante() {
        return fechaComprobante;
    }
    /**
     * Establece la fecha en la que se emite el comprobante.
     *
     * @param fechaComprobante la nueva fecha del comprobante
     */
    public void setFechaComprobante(LocalDate fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }
    /**
     * Obtiene el pago asociado a este comprobante.
     *
     * @return el pago correspondiente
     */
    public Pago getPago() {
        return pago;
    }
    /**
     * Establece el pago asociado a este comprobante.
     *
     * @param pago el nuevo pago correspondiente
     */
    public void setPago(Pago pago) {
        this.pago = pago;
    }

}
