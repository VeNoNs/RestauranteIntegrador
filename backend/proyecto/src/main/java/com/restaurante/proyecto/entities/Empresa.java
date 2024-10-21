
package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name="administrador_empresa")

public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idempresa")
    private Long idEmpresa;
    
    @Column(name="nombreEmpresa", nullable = false, length = 50)
    private String nombreEmpresa;
    @Column(name="telefono", nullable = false, length = 15)
    private String telefono; 
    @Column(name="usuario", nullable = false, length = 50)
    private String usuario;
    @Column(name="password", nullable = false, length = 50)
    private String password;
    
    public Empresa() {

    }

    public Empresa(Long idEmpresa, String nombreEmpresa, String telefono, String usuario, String password) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
    }

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
