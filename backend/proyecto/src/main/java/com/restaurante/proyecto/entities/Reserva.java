package com.restaurante.proyecto.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
/**
 * La entidad {@code Reserva} representa una reserva realizada en el sistema de gestión de un restaurante.
 * Contiene información sobre la cantidad de personas, la fecha y hora de la reserva, y la mesa asociada.
 */
@Entity
@Table(name = "reserva")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Long idReserva; // Identificador único de la reserva

    @Column(name = "cantidadPersonas", nullable = false)
    private int cantidadPersonas; // Cantidad de personas para la reserva

    @Column(name = "fechaReserva", nullable = false)
    private LocalDate fechaReserva; // Fecha en que se realiza la reserva

    @Column(name = "horaReserva", nullable = false)
    private LocalTime horaReserva; // Hora de la reserva

    @ManyToOne
    @JoinColumn(name = "idMesa", nullable = false)
    private Mesa mesa; // Relación con la entidad Mesa
    /**
     * Constructor por defecto para la clase {@code Reserva}.
     */
    public Reserva() {
    }
    /**
     * Constructor parametrizado para la clase {@code Reserva}.
     *
     * @param idReserva        el identificador de la reserva
     * @param cantidadPersonas la cantidad de personas para la reserva
     * @param fechaReserva     la fecha en que se realiza la reserva
     * @param horaReserva      la hora de la reserva
     * @param mesa             la mesa asociada a esta reserva
     */
    public Reserva(Long idReserva, int cantidadPersonas, LocalDate fechaReserva, LocalTime horaReserva, Mesa mesa) {
        this.idReserva = idReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.mesa = mesa;
    }
    /**
     * Obtiene el identificador de la reserva.
     *
     * @return el identificador de la reserva
     */
    public Long getIdReserva() {
        return idReserva;
    }
    /**
     * Establece el identificador de la reserva.
     *
     * @param idReserva el nuevo identificador de la reserva
     */
    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
    /**
     * Obtiene la cantidad de personas para la reserva.
     *
     * @return la cantidad de personas
     */
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }
    /**
     * Establece la cantidad de personas para la reserva.
     *
     * @param cantidadPersonas la nueva cantidad de personas
     */
    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    /**
     * Obtiene la fecha en que se realiza la reserva.
     *
     * @return la fecha de la reserva
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    /**
     * Establece la fecha en que se realiza la reserva.
     *
     * @param fechaReserva la nueva fecha de la reserva
     */
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    /**
     * Obtiene la hora de la reserva.
     *
     * @return la hora de la reserva
     */
    public LocalTime getHoraReserva() {
        return horaReserva;
    }
    /**
     * Establece la hora de la reserva.
     *
     * @param horaReserva la nueva hora de la reserva
     */
    public void setHoraReserva(LocalTime horaReserva) {
        this.horaReserva = horaReserva;
    }
    /**
     * Obtiene la mesa asociada a esta reserva.
     *
     * @return la mesa relacionada
     */
    public Mesa getMesa() {
        return mesa;
    }
    /**
     * Establece la mesa asociada a esta reserva.
     *
     * @param mesa la nueva mesa relacionada
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
