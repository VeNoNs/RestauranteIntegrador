package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador_restaurante")
public class AdministradorRestaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdminRestaurante")
    private Long idAdminRestaurante;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = true)
    private EmpresaComida empresa;

    public AdministradorRestaurante() {
    }

    public AdministradorRestaurante(Long idAdminRestaurante, String correo, String password, EmpresaComida empresa) {
        this.idAdminRestaurante = idAdminRestaurante;
        this.correo = correo;
        this.password = password;
        this.empresa = empresa;
    }

    // Getters y Setters
    public Long getIdAdminRestaurante() {
        return idAdminRestaurante;
    }

    public void setIdAdminRestaurante(Long idAdminRestaurante) {
        this.idAdminRestaurante = idAdminRestaurante;
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

    public EmpresaComida getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaComida empresa) {
        this.empresa = empresa;
    }
}
