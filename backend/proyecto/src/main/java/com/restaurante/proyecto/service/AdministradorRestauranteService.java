package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.AdministradorRestaurante;
import java.util.List;

public interface AdministradorRestauranteService {

    List<AdministradorRestaurante> obtenerTodos();

    AdministradorRestaurante obtenerPorId(Long id);

    AdministradorRestaurante crearAdministradorRestaurante(AdministradorRestaurante adminRestaurante);

    AdministradorRestaurante actualizarAdministradorRestaurante(Long id, AdministradorRestaurante adminRestaurante);

    void eliminarAdministradorRestaurante(Long id);

    long contarAdministradorRestaurante();
}
