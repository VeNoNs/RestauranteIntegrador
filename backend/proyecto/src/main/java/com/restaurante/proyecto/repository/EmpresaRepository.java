package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmpresaRepository extends JpaRepository<Empresa,Long>{
    
}
