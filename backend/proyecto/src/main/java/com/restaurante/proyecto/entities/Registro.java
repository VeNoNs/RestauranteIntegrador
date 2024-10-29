package com.restaurante.proyecto.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "registro")
public class Registro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegistro")
    private Long idRegistro;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "idComensal", nullable = false)
    private Comensal comensal;

    public Registro() {
    }

    public Registro(Long idRegistro, LocalDate fechaRegistro, Comensal comensal) {
        this.idRegistro = idRegistro;
        this.fechaRegistro = fechaRegistro;
        this.comensal = comensal;
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Comensal getComensal() {
        return comensal;
    }

    public void setComensal(Comensal comensal) {
        this.comensal = comensal;
    }
}
