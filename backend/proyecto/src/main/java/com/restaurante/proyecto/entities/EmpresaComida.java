package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa_comida")
public class EmpresaComida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idempresa")
    private Long idEmpresa;

    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;

    public EmpresaComida() {
    }

    public EmpresaComida(Long idEmpresa, String ubicacion) {
        this.idEmpresa = idEmpresa;
        this.ubicacion = ubicacion;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
