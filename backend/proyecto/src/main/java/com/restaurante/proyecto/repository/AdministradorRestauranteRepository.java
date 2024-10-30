package com.restaurante.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restaurante.proyecto.entities.AdministradorRestaurante;
/**
 * Repositorio para gestionar {@code AdministradorRestaurante}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface AdministradorRestauranteRepository extends JpaRepository<AdministradorRestaurante, Long> {
    
     AdministradorRestaurante findByCorreoAndPassword(String correo, String password);
}