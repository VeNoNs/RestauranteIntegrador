package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa a un comensal en el sistema
 * de gestión del menú de un restaurante.
 *
 * Esta entidad almacena la información de un comensal, incluyendo
 * su nombre, apellido, correo y contraseña.
 */
@Entity
@Table(name = "comensal")
public class Comensal {
    /**
     * Identificador único del comensal.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComensal")
    private Long idComensal;
    /**
     * Nombre del comensal.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "nombreComensal", nullable = false, length = 50)
    private String nombreComensal;
    /**
     * Apellido del comensal.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "apellidoComensal", nullable = false, length = 50)
    private String apellidoComensal;
    /**
     * Correo electrónico del comensal.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    /**
     * Contraseña del comensal para acceder al sistema.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    /**
     * Constructor vacío para la entidad Comensal.
     * Utilizado por el framework para la creación de instancias.
     */
    public Comensal() {
    }
    /**
     * Constructor con parámetros para crear una instancia de Comensal.
     *
     * @param idComensal el identificador único del comensal
     * @param nombreComensal el nombre del comensal
     * @param apellidoComensal el apellido del comensal
     * @param correo el correo electrónico del comensal
     * @param password la contraseña del comensal
     */
    public Comensal(Long idComensal, String nombreComensal, String apellidoComensal, String correo, String password) {
        this.idComensal = idComensal;
        this.nombreComensal = nombreComensal;
        this.apellidoComensal = apellidoComensal;
        this.correo = correo;
        this.password = password;
    }
    /**
     * Obtiene el identificador único del comensal.
     *
     * @return el id del comensal
     */
    public Long getIdComensal() {
        return idComensal;
    }
    /**
     * Establece el identificador único del comensal.
     *
     * @param idComensal el nuevo id del comensal
     */
    public void setIdComensal(Long idComensal) {
        this.idComensal = idComensal;
    }
    /**
     * Obtiene el nombre del comensal.
     *
     * @return el nombre del comensal
     */
    public String getNombreComensal() {
        return nombreComensal;
    }
    /**
     * Establece el nombre del comensal.
     *
     * @param nombreComensal el nuevo nombre del comensal
     */
    public void setNombreComensal(String nombreComensal) {
        this.nombreComensal = nombreComensal;
    }
    /**
     * Obtiene el apellido del comensal.
     *
     * @return el apellido del comensal
     */
    public String getApellidoComensal() {
        return apellidoComensal;
    }
    /**
     * Establece el apellido del comensal.
     *
     * @param apellidoComensal el nuevo apellido del comensal
     */
    public void setApellidoComensal(String apellidoComensal) {
        this.apellidoComensal = apellidoComensal;
    }
    /**
     * Obtiene el correo electrónico del comensal.
     *
     * @return el correo del comensal
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Establece el correo electrónico del comensal.
     *
     * @param correo el nuevo correo del comensal
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Obtiene la contraseña del comensal.
     *
     * @return la contraseña del comensal
     */
    public String getPassword() {
        return password;
    }
    /**
     * Establece la contraseña del comensal.
     *
     * @param password la nueva contraseña del comensal
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
