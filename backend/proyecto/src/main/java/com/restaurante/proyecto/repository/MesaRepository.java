package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Mesa}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    // Puedes agregar métodos de consulta personalizados si lo necesitas
}
