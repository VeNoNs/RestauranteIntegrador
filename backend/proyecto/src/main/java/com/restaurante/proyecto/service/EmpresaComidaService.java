package com.restaurante.proyecto.service;

import java.util.List;
import com.restaurante.proyecto.entities.EmpresaComida;

public interface EmpresaComidaService {
    
    List<EmpresaComida> obtenerTodos();

    EmpresaComida obtenerPorId(Long id);

    EmpresaComida crearEmpresa(EmpresaComida empresa);

    EmpresaComida actualizarEmpresa(Long id, EmpresaComida empresa);

    void eliminarEmpresa(Long id);

    long contarEmpresa();
}
