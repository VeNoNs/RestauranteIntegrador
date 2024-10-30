package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code Empleado}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
        Empleado findByCorreoAndPassword(String correo, String password);
}
