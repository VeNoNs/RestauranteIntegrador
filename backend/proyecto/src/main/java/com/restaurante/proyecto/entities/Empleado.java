package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Long idEmpleado;

    @Column(name = "nombreEmpleado", nullable = false, length = 50)
    private String nombreEmpleado;

    @Column(name = "apellidoEmpleado", nullable = false, length = 50)
    private String apellidoEmpleado;

    @Column(name = "tipoEmpleado", nullable = false, length = 20)
    private String tipoEmpleado;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    // Relación con Local en lugar de EmpresaComida
    @ManyToOne
    @JoinColumn(name = "idLocal", nullable = false)
    private Local local;

    public Empleado() {
    }

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
    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
