package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesa")  // Cambiado a "idMesa" para que coincida con la tabla
    private Long idMesa;

    @Column(name = "nroMesa", nullable = false)
    private int nroMesa;

    @Column(name = "cantidadSillas", nullable = false)
    private int cantidadSillas;

    @Column(name = "estado", nullable = false, length = 20)  // Cambié el tamaño a 20 para que coincida con la tabla
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idLocal", nullable = false)  // Relación con Local
    private Local local;

    public Mesa() {
    }

    public Mesa(Long idMesa, int nroMesa, int cantidadSillas, String estado, Local local) {
        this.idMesa = idMesa;
        this.nroMesa = nroMesa;
        this.cantidadSillas = cantidadSillas;
        this.estado = estado;
        this.local = local;
    }

    public Long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    public int getNroMesa() {
        return nroMesa;
    }

    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }

    public int getCantidadSillas() {
        return cantidadSillas;
    }

    public void setCantidadSillas(int cantidadSillas) {
        this.cantidadSillas = cantidadSillas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
