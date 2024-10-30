package com.restaurante.proyecto.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
/**
 * La entidad {@code Registro} representa un registro de un comensal en el sistema de gestión de un restaurante.
 * Contiene información sobre la fecha en que se realizó el registro y la relación con el comensal correspondiente.
 */
@Entity
@Table(name = "registro")
public class Registro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegistro")
    private Long idRegistro; // Identificador único del registro

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro; // Fecha en que se realizó el registro

    @ManyToOne
    @JoinColumn(name = "idComensal", nullable = false)
    private Comensal comensal; // Relación con la entidad Comensal
    /**
     * Constructor por defecto para la clase {@code Registro}.
     */
    public Registro() {
    }
    /**
     * Constructor parametrizado para la clase {@code Registro}.
     *
     * @param idRegistro    el identificador del registro
     * @param fechaRegistro la fecha en que se realizó el registro
     * @param comensal     el comensal relacionado con este registro
     */
    public Registro(Long idRegistro, LocalDate fechaRegistro, Comensal comensal) {
        this.idRegistro = idRegistro;
        this.fechaRegistro = fechaRegistro;
        this.comensal = comensal;
    }
    /**
     * Obtiene el identificador del registro.
     *
     * @return el identificador del registro
     */
    public Long getIdRegistro() {
        return idRegistro;
    }
    /**
     * Establece el identificador del registro.
     *
     * @param idRegistro el nuevo identificador del registro
     */
    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }
    /**
     * Obtiene la fecha en que se realizó el registro.
     *
     * @return la fecha del registro
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
    /**
     * Establece la fecha en que se realizó el registro.
     *
     * @param fechaRegistro la nueva fecha del registro
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    /**
     * Obtiene el comensal relacionado con este registro.
     *
     * @return el comensal relacionado
     */
    public Comensal getComensal() {
        return comensal;
    }
    /**
     * Establece el comensal relacionado con este registro.
     *
     * @param comensal el nuevo comensal relacionado
     */
    public void setComensal(Comensal comensal) {
        this.comensal = comensal;
    }
}
