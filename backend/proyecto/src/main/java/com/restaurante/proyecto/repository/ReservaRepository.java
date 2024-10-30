package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Reserva}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Métodos personalizados si son necesarios
}
