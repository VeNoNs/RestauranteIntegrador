package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name="administrador_general")
public class AdministradorEmpresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin_general")
    private Long idAdminGeneral;
    
    @Column(name="nombrePersona", nullable = false, length = 50)
    private String nombrePersona;
    
    @Column(name="telefono", nullable = false, length = 15)
    private String telefono;
    
    @Column(name="usuario", nullable = false, length = 50)
    private String usuario;
    
    @Column(name="password", nullable = false, length = 50)
    private String password;
    
    public AdministradorEmpresa() {
    }

    public AdministradorEmpresa(Long idAdminGeneral, String nombrePersona, String telefono, String usuario, String password) {
        this.idAdminGeneral = idAdminGeneral;
        this.nombrePersona = nombrePersona;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
    }

    // Getters y Setters
    public Long getIdAdminGeneral() {
        return idAdminGeneral;
    }

    public void setIdAdminGeneral(Long idAdminGeneral) {
        this.idAdminGeneral = idAdminGeneral;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
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
