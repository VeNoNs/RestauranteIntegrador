package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa al administrador de un restaurante en el sistema
 * de gestión del menú de un restaurante.
 *
 * Esta entidad almacena la información del administrador de un restaurante
 * específico, incluyendo su correo, contraseña y la empresa de comida a la
 * que pertenece.
 */
@Entity
@Table(name = "administrador_restaurante")
public class AdministradorRestaurante {
    /**
     * Identificador único del administrador del restaurante.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdminRestaurante")
    private Long idAdminRestaurante;
    /**
     * Correo electrónico del administrador del restaurante.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    /**
     * Contraseña del administrador del restaurante para acceder al sistema.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    /**
     * Empresa de comida a la que pertenece el administrador del restaurante.
     * Este campo es opcional (nullable).
     */
    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = true)
    private EmpresaComida empresa;
    /**
     * Constructor vacío para la entidad AdministradorRestaurante.
     * Utilizado por el framework para la creación de instancias.
     */
    public AdministradorRestaurante() {
    }
    /**
     * Constructor con parámetros para crear una instancia de AdministradorRestaurante.
     *
     * @param idAdminRestaurante el identificador único del administrador
     * @param correo el correo electrónico del administrador
     * @param password la contraseña del administrador
     * @param empresa la empresa de comida a la que pertenece el administrador
     */
    public AdministradorRestaurante(Long idAdminRestaurante, String correo, String password, EmpresaComida empresa) {
        this.idAdminRestaurante = idAdminRestaurante;
        this.correo = correo;
        this.password = password;
        this.empresa = empresa;
    }

    /**
     * Obtiene el identificador único del administrador del restaurante.
     *
     * @return el id del administrador del restaurante
     */
    public Long getIdAdminRestaurante() {
        return idAdminRestaurante;
    }
    /**
     * Establece el identificador único del administrador del restaurante.
     *
     * @param idAdminRestaurante el nuevo id del administrador del restaurante
     */
    public void setIdAdminRestaurante(Long idAdminRestaurante) {
        this.idAdminRestaurante = idAdminRestaurante;
    }
    /**
     * Obtiene el correo electrónico del administrador del restaurante.
     *
     * @return el correo del administrador
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Establece el correo electrónico del administrador del restaurante.
     *
     * @param correo el nuevo correo del administrador
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Obtiene la contraseña del administrador del restaurante.
     *
     * @return la contraseña del administrador
     */
    public String getPassword() {
        return password;
    }
    /**
     * Establece la contraseña del administrador del restaurante.
     *
     * @param password la nueva contraseña del administrador
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Obtiene la empresa de comida a la que pertenece el administrador del restaurante.
     *
     * @return la empresa de comida del administrador
     */
    public EmpresaComida getEmpresa() {
        return empresa;
    }
    /**
     * Establece la empresa de comida a la que pertenece el administrador del restaurante.
     *
     * @param empresa la nueva empresa de comida del administrador
     */
    public void setEmpresa(EmpresaComida empresa) {
        this.empresa = empresa;
    }
}
