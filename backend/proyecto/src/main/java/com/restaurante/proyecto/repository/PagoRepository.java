package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Pago}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
}
