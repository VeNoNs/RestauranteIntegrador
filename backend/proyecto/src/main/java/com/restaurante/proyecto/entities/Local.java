package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocal")
    private Long idLocal;

    @Column(name = "nroMesa", nullable = false)
    private int nroMesa;

    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false)
    private EmpresaComida empresa;

    public Local() {
    }

    public Local(Long idLocal, int nroMesa, String ubicacion, EmpresaComida empresa) {
        this.idLocal = idLocal;
        this.nroMesa = nroMesa;
        this.ubicacion = ubicacion;
        this.empresa = empresa;
    }

    // Getters y Setters
    public Long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }

    public int getNroMesa() {
        return nroMesa;
    }

    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EmpresaComida getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaComida empresa) {
        this.empresa = empresa;
    }
}
