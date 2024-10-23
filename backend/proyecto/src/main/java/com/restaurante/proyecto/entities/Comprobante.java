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

    @Column(name = "idPago", nullable = false, length = 5)
    private String idPago;

    public Comprobante() {
    }

    public Comprobante(Long idComprobante, LocalDate fechaComprobante, String idPago) {
        this.idComprobante = idComprobante;
        this.fechaComprobante = fechaComprobante;
        this.idPago = idPago;
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

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

}
