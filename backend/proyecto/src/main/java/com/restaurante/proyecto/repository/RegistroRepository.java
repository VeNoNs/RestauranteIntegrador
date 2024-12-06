package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Registro}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    // Métodos personalizados si es necesario
}
