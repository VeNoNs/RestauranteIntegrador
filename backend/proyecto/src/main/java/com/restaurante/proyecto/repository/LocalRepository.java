package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Local}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
}
