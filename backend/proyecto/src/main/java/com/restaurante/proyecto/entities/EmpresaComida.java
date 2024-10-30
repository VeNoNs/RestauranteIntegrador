package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa una empresa de comida en el sistema de gestión.
 *
 * Cada empresa de comida tiene un nombre y un teléfono de contacto.
 */
@Entity
@Table(name = "empresa_comida")
public class EmpresaComida {
    /**
     * Identificador único de la empresa de comida.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpresa")
    private Long idEmpresa;
    /**
     * Nombre de la empresa de comida.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "nombreEmpresa", nullable = false, length = 50)
    private String nombreEmpresa;
    /**
     * Teléfono de contacto de la empresa de comida.
     * Este campo es obligatorio y tiene un límite de 15 caracteres.
     */
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;
    /**
     * Constructor vacío para la entidad EmpresaComida.
     * Utilizado por el framework para la creación de instancias.
     */
    public EmpresaComida() {
    }
    /**
     * Constructor con parámetros para crear una instancia de EmpresaComida.
     *
     * @param idEmpresa el identificador único de la empresa
     * @param nombreEmpresa el nombre de la empresa
     * @param telefono el teléfono de contacto de la empresa
     */
    public EmpresaComida(Long idEmpresa, String nombreEmpresa, String telefono) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único de la empresa de comida.
     *
     * @return el id de la empresa
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }
    /**
     * Establece el identificador único de la empresa de comida.
     *
     * @param idEmpresa el nuevo id de la empresa
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    /**
     * Obtiene el nombre de la empresa de comida.
     *
     * @return el nombre de la empresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    /**
     * Establece el nombre de la empresa de comida.
     *
     * @param nombreEmpresa el nuevo nombre de la empresa
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    /**
     * Obtiene el teléfono de contacto de la empresa de comida.
     *
     * @return el teléfono de la empresa
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Establece el teléfono de contacto de la empresa de comida.
     *
     * @param telefono el nuevo teléfono de la empresa
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
