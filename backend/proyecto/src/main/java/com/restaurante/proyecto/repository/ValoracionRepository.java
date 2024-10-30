package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Valoracion}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    // Métodos personalizados si es necesario
}
