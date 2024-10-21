package com.restaurante.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comensal")
public class Comensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomensal")
    private Long idComensal;

    @Column(name = "nombre_comensal", nullable = false, length = 50)
    private String nombreComensal;

    @Column(name = "apellido_comensal", nullable = false, length = 50)
    private String apellidoComensal;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    public Comensal() {
    }

    public Comensal(Long idComensal, String nombreComensal, String apellidoComensal, String correo, String password) {
        this.idComensal = idComensal;
        this.nombreComensal = nombreComensal;
        this.apellidoComensal = apellidoComensal;
        this.correo = correo;
        this.password = password;
    }

    public Long getIdComensal() {
        return idComensal;
    }

    public void setIdComensal(Long idComensal) {
        this.idComensal = idComensal;
    }

    public String getNombreComensal() {
        return nombreComensal;
    }

    public void setNombreComensal(String nombreComensal) {
        this.nombreComensal = nombreComensal;
    }

    public String getApellidoComensal() {
        return apellidoComensal;
    }

    public void setApellidoComensal(String apellidoComensal) {
        this.apellidoComensal = apellidoComensal;
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
}
