package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "valoracion")
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idValoracion")
    private Long idValoracion;

    @Column(name = "reseña", nullable = false, columnDefinition = "TEXT")
    private String reseña;

    @ManyToOne
    @JoinColumn(name = "idComensal", nullable = false)
    private Comensal comensal;

    public Valoracion() {
    }

    public Valoracion(Long idValoracion, String reseña, Comensal comensal) {
        this.idValoracion = idValoracion;
        this.reseña = reseña;
        this.comensal = comensal;
    }

    public Long getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(Long idValoracion) {
        this.idValoracion = idValoracion;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public Comensal getComensal() {
        return comensal;
    }

    public void setComensal(Comensal comensal) {
        this.comensal = comensal;
    }
}
