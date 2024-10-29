package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa_comida")
public class EmpresaComida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpresa")
    private Long idEmpresa;

    @Column(name = "nombreEmpresa", nullable = false, length = 50)
    private String nombreEmpresa;

    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    public EmpresaComida() {
    }

    public EmpresaComida(Long idEmpresa, String nombreEmpresa, String telefono) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
