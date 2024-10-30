package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.EmpresaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio para gestionar {@code EmpresaComida}.
 * Extiende {@code JpaRepository} para proporcionar operaciones CRUD.
 */
@Repository
public interface EmpresaComidaRepository extends JpaRepository<EmpresaComida, Long> {
}
