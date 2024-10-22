package com.restaurante.proyecto.service;

import java.util.List;

import com.restaurante.proyecto.entities.*;

public interface AdministradorEmpresaService {
    List<AdministradorEmpresa> obtenerTodos();

    AdministradorEmpresa obtenerPorId(Long id);

    AdministradorEmpresa crearEmpresa(AdministradorEmpresa empresa);

    AdministradorEmpresa actualizarEmpresa(Long id, AdministradorEmpresa empresa);

    void eliminarEmpresa(Long id);

    long contarEmpresa();
    
}
