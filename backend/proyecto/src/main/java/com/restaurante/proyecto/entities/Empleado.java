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

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private EmpresaComida empresa;

    public Empleado() {
    }

    public Empleado(Long idEmpleado, String nombreEmpleado, String apellidoEmpleado, String tipoEmpleado, EmpresaComida empresa) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.tipoEmpleado = tipoEmpleado;
        this.empresa = empresa;
    }

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

    public EmpresaComida getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaComida empresa) {
        this.empresa = empresa;
    }
}
