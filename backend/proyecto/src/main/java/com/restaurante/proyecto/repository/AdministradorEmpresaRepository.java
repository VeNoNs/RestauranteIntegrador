package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code AdministradorEmpresa}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface AdministradorEmpresaRepository extends JpaRepository<AdministradorEmpresa, Long> {

     /**
      * Encuentra un administrador de empresa por su nombre de usuario y contraseña.
      *
      * @param usuario el nombre de usuario del administrador
      * @param password la contraseña del administrador
      * @return el {@code AdministradorEmpresa} correspondiente, o {@code null} si no existe
      */
     AdministradorEmpresa findByUsuarioAndPassword(String usuario, String password);
}