package com.restaurante.proyecto.entities;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "comprobante")
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComprobante")
    private Long idComprobante;

    @Column(name = "fechaComprobante", nullable = false)
    private LocalDate fechaComprobante;

    @ManyToOne
    @JoinColumn(name = "idPago", nullable = false)
    private Pago pago;

    public Comprobante() {
    }

    public Comprobante(Long idComprobante, LocalDate fechaComprobante, Pago pago) {
        this.idComprobante = idComprobante;
        this.fechaComprobante = fechaComprobante;
        this.pago = pago;
    }

    public Long getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Long idComprobante) {
        this.idComprobante = idComprobante;
    }

    public LocalDate getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(LocalDate fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

}
