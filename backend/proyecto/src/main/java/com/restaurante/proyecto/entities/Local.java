package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpresa")
    private Long idEmpresa;

    @Column(name = "nroMesa", nullable = false)
    private int nroMesa;

    public Local() {
    }

    public Local(Long idEmpresa, int nroMesa) {
        this.idEmpresa = idEmpresa;
        this.nroMesa = nroMesa;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getNroMesa() {
        return nroMesa;
    }

    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }
}
