package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa un local en el sistema de gestión de restaurantes.
 *
 * Un local tiene un número de mesa, una ubicación y está asociado a una empresa de comida.
 */
@Entity
@Table(name = "local")
public class Local {
    /**
     * Identificador único del local.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocal")
    private Long idLocal;
    /**
     * Número de mesa asociado al local.
     * Este campo es obligatorio.
     */
    @Column(name = "nroMesa", nullable = false)
    private int nroMesa;
    /**
     * Ubicación del local.
     * Este campo es obligatorio y tiene un límite de 100 caracteres.
     */
    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;
    /**
     * Relación con la empresa de comida a la que pertenece el local.
     * Este campo es obligatorio.
     */
    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private EmpresaComida empresa;
    /**
     * Constructor vacío para la entidad Local.
     * Utilizado por el framework para la creación de instancias.
     */
    public Local() {
    }
    /**
     * Constructor con parámetros para crear una instancia de Local.
     *
     * @param idLocal el identificador único del local
     * @param nroMesa el número de mesa del local
     * @param ubicacion la ubicación del local
     * @param empresa la empresa de comida asociada al local
     */
    public Local(Long idLocal, int nroMesa, String ubicacion, EmpresaComida empresa) {
        this.idLocal = idLocal;
        this.nroMesa = nroMesa;
        this.ubicacion = ubicacion;
        this.empresa = empresa;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único del local.
     *
     * @return el id del local
     */
    public Long getIdLocal() {
        return idLocal;
    }
    /**
     * Establece el identificador único del local.
     *
     * @param idLocal el nuevo id del local
     */
    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }
    /**
     * Obtiene el número de mesa del local.
     *
     * @return el número de mesa
     */
    public int getNroMesa() {
        return nroMesa;
    }
    /**
     * Establece el número de mesa del local.
     *
     * @param nroMesa el nuevo número de mesa
     */
    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }
    /**
     * Obtiene la ubicación del local.
     *
     * @return la ubicación del local
     */
    public String getUbicacion() {
        return ubicacion;
    }
    /**
     * Establece la ubicación del local.
     *
     * @param ubicacion la nueva ubicación del local
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    /**
     * Obtiene la empresa de comida asociada al local.
     *
     * @return la empresa de comida
     */
    public EmpresaComida getEmpresa() {
        return empresa;
    }
    /**
     * Establece la empresa de comida asociada al local.
     *
     * @param empresa la nueva empresa de comida
     */
    public void setEmpresa(EmpresaComida empresa) {
        this.empresa = empresa;
    }
}
