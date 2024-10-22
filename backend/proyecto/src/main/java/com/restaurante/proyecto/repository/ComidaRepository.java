package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Long> {
}
