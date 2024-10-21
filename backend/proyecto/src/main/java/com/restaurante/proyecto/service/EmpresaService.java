package com.restaurante.proyecto.service;

import java.util.List;

import com.restaurante.proyecto.entities.*;

public interface EmpresaService {
    List<Empresa> obtenerTodos();

    Empresa obtenerPorId(Long id);

    Empresa crearEmpresa(Empresa empresa);

    Empresa actualizarEmpresa(Long id, Empresa empresa);

    void eliminarEmpresa(Long id);

    long contarEmpresa();
    
}
