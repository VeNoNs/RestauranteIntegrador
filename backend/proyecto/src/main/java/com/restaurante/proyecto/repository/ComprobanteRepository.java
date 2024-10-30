package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Comprobante}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
}
