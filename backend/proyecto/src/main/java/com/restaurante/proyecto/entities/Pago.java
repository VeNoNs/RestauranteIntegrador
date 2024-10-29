package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPago")
    private Long idPago;

    @Column(name = "fechaPago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "monto", nullable = false)
    private double monto;

    @ManyToOne
    @JoinColumn(name = "idOrden", nullable = false)
    private Orden orden;  // Relación con la entidad Orden

    public Pago() {
    }

    public Pago(Long idPago, LocalDate fechaPago, double monto, Orden orden) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.orden = orden;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
