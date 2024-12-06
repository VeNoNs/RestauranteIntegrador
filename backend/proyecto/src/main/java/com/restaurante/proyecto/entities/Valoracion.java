package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * La entidad {@code Valoracion} representa una valoración realizada por un comensal en el sistema de gestión de un restaurante.
 * Contiene una reseña escrita por el comensal y establece una relación con la entidad {@code Comensal}.
 */
@Entity
@Table(name = "valoracion")
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idValoracion")
    private Long idValoracion; // Identificador único de la valoración

    @Column(name = "reseña", nullable = false, columnDefinition = "TEXT")
    private String reseña; // Reseña escrita por el comensal

    @ManyToOne
    @JoinColumn(name = "idComensal", nullable = false)
    private Comensal comensal; // Relación con la entidad Comensal
    /**
     * Constructor por defecto para la clase {@code Valoracion}.
     */
    public Valoracion() {
    }
    /**
     * Constructor parametrizado para la clase {@code Valoracion}.
     *
     * @param idValoracion el identificador de la valoración
     * @param reseña      la reseña escrita por el comensal
     * @param comensal    el comensal que realiza la valoración
     */
    public Valoracion(Long idValoracion, String reseña, Comensal comensal) {
        this.idValoracion = idValoracion;
        this.reseña = reseña;
        this.comensal = comensal;
    }
    /**
     * Obtiene el identificador de la valoración.
     *
     * @return el identificador de la valoración
     */
    public Long getIdValoracion() {
        return idValoracion;
    }
    /**
     * Establece el identificador de la valoración.
     *
     * @param idValoracion el nuevo identificador de la valoración
     */
    public void setIdValoracion(Long idValoracion) {
        this.idValoracion = idValoracion;
    }
    /**
     * Obtiene la reseña escrita por el comensal.
     *
     * @return la reseña
     */
    public String getReseña() {
        return reseña;
    }
    /**
     * Establece la reseña escrita por el comensal.
     *
     * @param reseña la nueva reseña
     */
    public void setReseña(String reseña) {
        this.reseña = reseña;
    }
    /**
     * Obtiene el comensal que realizó la valoración.
     *
     * @return el comensal
     */
    public Comensal getComensal() {
        return comensal;
    }
    /**
     * Establece el comensal que realizó la valoración.
     *
     * @param comensal el nuevo comensal
     */
    public void setComensal(Comensal comensal) {
        this.comensal = comensal;
    }
}
