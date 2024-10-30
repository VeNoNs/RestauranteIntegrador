package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Orden}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
