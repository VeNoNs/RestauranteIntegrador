package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Comensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Comensal}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface ComensalRepository extends JpaRepository<Comensal, Long> {
    
    Comensal findByCorreoAndPassword(String correo, String password);
}
