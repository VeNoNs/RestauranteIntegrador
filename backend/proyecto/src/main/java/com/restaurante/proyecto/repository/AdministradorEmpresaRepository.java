package com.restaurante.proyecto.repository;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorEmpresaRepository extends JpaRepository<AdministradorEmpresa, Long> {
    // Aquí puedes añadir métodos personalizados si es necesario
    
     AdministradorEmpresa findByUsuarioAndPassword(String usuario, String password);
}