package com.restaurante.proyecto.entities;

import jakarta.persistence.*;
/**
 * Clase que representa un empleado en el sistema de gestión de un restaurante.
 *
 * Los empleados están vinculados a un local específico y tienen información
 * como su nombre, apellido, tipo de empleado, correo y contraseña.
 */
@Entity
@Table(name = "empleado")
public class Empleado {
    /**
     * Identificador único del empleado.
     * Este valor es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Long idEmpleado;
    /**
     * Nombre del empleado.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "nombreEmpleado", nullable = false, length = 50)
    private String nombreEmpleado;
    /**
     * Apellido del empleado.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "apellidoEmpleado", nullable = false, length = 50)
    private String apellidoEmpleado;
    /**
     * Tipo de empleado (por ejemplo, "Cocinero", "Mesero").
     * Este campo es obligatorio y tiene un límite de 20 caracteres.
     */
    @Column(name = "tipoEmpleado", nullable = false, length = 20)
    private String tipoEmpleado;
    /**
     * Correo electrónico del empleado.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    /**
     * Contraseña del empleado.
     * Este campo es obligatorio y tiene un límite de 50 caracteres.
     */
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    /**
     * Relación muchos a uno con la entidad Local.
     * Indica el local al que está asignado el empleado.
     */
    @ManyToOne
    @JoinColumn(name = "idLocal", nullable = false)
    private Local local;
    /**
     * Constructor vacío para la entidad Empleado.
     * Utilizado por el framework para la creación de instancias.
     */
    public Empleado() {
    }
    /**
     * Constructor con parámetros para crear una instancia de Empleado.
     *
     * @param idEmpleado el identificador único del empleado
     * @param nombreEmpleado el nombre del empleado
     * @param apellidoEmpleado el apellido del empleado
     * @param tipoEmpleado el tipo de empleado (cargo)
     * @param correo el correo electrónico del empleado
     * @param password la contraseña del empleado
     * @param local el local donde trabaja el empleado
     */
    public Empleado(Long idEmpleado, String nombreEmpleado, String apellidoEmpleado, String tipoEmpleado, String correo, String password, Local local) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.tipoEmpleado = tipoEmpleado;
        this.correo = correo;
        this.password = password;
        this.local = local;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único del empleado.
     *
     * @return el id del empleado
     */
    public Long getIdEmpleado() {
        return idEmpleado;
    }
    /**
     * Establece el identificador único del empleado.
     *
     * @param idEmpleado el nuevo id del empleado
     */
    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    /**
     * Obtiene el nombre del empleado.
     *
     * @return el nombre del empleado
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
    /**
     * Establece el nombre del empleado.
     *
     * @param nombreEmpleado el nuevo nombre del empleado
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    /**
     * Obtiene el apellido del empleado.
     *
     * @return el apellido del empleado
     */
    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }
    /**
     * Establece el apellido del empleado.
     *
     * @param apellidoEmpleado el nuevo apellido del empleado
     */
    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }
    /**
     * Obtiene el tipo de empleado (cargo).
     *
     * @return el tipo de empleado
     */
    public String getTipoEmpleado() {
        return tipoEmpleado;
    }
    /**
     * Establece el tipo de empleado.
     *
     * @param tipoEmpleado el nuevo tipo de empleado
     */
    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
    /**
     * Obtiene el correo electrónico del empleado.
     *
     * @return el correo del empleado
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Establece el correo electrónico del empleado.
     *
     * @param correo el nuevo correo del empleado
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Obtiene la contraseña del empleado.
     *
     * @return la contraseña del empleado
     */
    public String getPassword() {
        return password;
    }
    /**
     * Establece la contraseña del empleado.
     *
     * @param password la nueva contraseña del empleado
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Obtiene el local donde trabaja el empleado.
     *
     * @return el local del empleado
     */
    public Local getLocal() {
        return local;
    }
    /**
     * Establece el local donde trabaja el empleado.
     *
     * @param local el nuevo local del empleado
     */
    public void setLocal(Local local) {
        this.local = local;
    }
}
