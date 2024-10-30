package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa al administrador general de una empresa dentro del sistema
 * de gestión del menú de un restaurante.
 *
 * Esta entidad es responsable de almacenar la información de los administradores
 * que gestionan el sistema, tales como su nombre, teléfono, usuario y contraseña.
 */
@Entity
@Table(name="administrador_general")
public class AdministradorEmpresa {
    /**
     * Identificador único del administrador general.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin_general")
    private Long idAdminGeneral;
    /**
     * Nombre de la persona que actúa como administrador general.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name="nombrePersona", nullable = false, length = 50)
    private String nombrePersona;
    /**
     * Número de teléfono del administrador general.
     * Este campo es obligatorio y tiene un límite de 15 caracteres.
     */
    @Column(name="telefono", nullable = false, length = 15)
    private String telefono;
    /**
     * Nombre de usuario del administrador general para iniciar sesión en el sistema.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name="usuario", nullable = false, length = 50)
    private String usuario;
    /**
     * Contraseña del administrador general para acceder al sistema.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name="password", nullable = false, length = 50)
    private String password;

    /**
     * Constructor vacío para la entidad AdministradorEmpresa.
     * Se utiliza principalmente por el framework para la creación de instancias.
     */
    public AdministradorEmpresa() {
    }
    /**
     * Constructor con parámetros para crear una instancia de AdministradorEmpresa.
     *
     * @param idAdminGeneral el identificador único del administrador
     * @param nombrePersona el nombre de la persona que actúa como administrador
     * @param telefono el número de teléfono del administrador
     * @param usuario el nombre de usuario para iniciar sesión
     * @param password la contraseña para el acceso al sistema
     */
    public AdministradorEmpresa(Long idAdminGeneral, String nombrePersona, String telefono, String usuario, String password) {
        this.idAdminGeneral = idAdminGeneral;
        this.nombrePersona = nombrePersona;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
    }

    /**
     * Obtiene el identificador único del administrador general.
     *
     * @return el id del administrador general
     */
    public Long getIdAdminGeneral() {
        return idAdminGeneral;
    }
    /**
     * Establece el identificador único del administrador general.
     *
     * @param idAdminGeneral el nuevo id del administrador general
     */
    public void setIdAdminGeneral(Long idAdminGeneral) {
        this.idAdminGeneral = idAdminGeneral;
    }
    /**
     * Obtiene el nombre de la persona que actúa como administrador general.
     *
     * @return el nombre del administrador
     */
    public String getNombrePersona() {
        return nombrePersona;
    }
    /**
     * Establece el nombre de la persona que actúa como administrador general.
     *
     * @param nombrePersona el nuevo nombre del administrador
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }
    /**
     * Obtiene el número de teléfono del administrador general.
     *
     * @return el teléfono del administrador
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Establece el número de teléfono del administrador general.
     *
     * @param telefono el nuevo teléfono del administrador
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Obtiene el nombre de usuario del administrador general.
     *
     * @return el nombre de usuario del administrador
     */
    public String getUsuario() {
        return usuario;
    }
    /**
     * Establece el nombre de usuario del administrador general.
     *
     * @param usuario el nuevo nombre de usuario del administrador
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
     * Obtiene la contraseña del administrador general.
     *
     * @return la contraseña del administrador
     */
    public String getPassword() {
        return password;
    }
    /**
     * Establece la contraseña del administrador general.
     *
     * @param password la nueva contraseña del administrador
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
