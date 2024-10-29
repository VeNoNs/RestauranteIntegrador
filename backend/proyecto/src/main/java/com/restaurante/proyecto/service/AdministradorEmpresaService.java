package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import java.util.List;

public interface AdministradorEmpresaService {

    List<AdministradorEmpresa> obtenerTodos();

    AdministradorEmpresa obtenerPorId(Long id);

    AdministradorEmpresa crearAdministradorGeneral(AdministradorEmpresa adminGeneral);

    AdministradorEmpresa actualizarAdministradorGeneral(Long id, AdministradorEmpresa adminGeneral);

    void eliminarAdministradorGeneral(Long id);

    long contarAdministradorGeneral();
}
