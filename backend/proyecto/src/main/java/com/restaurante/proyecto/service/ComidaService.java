package com.restaurante.proyecto.service;

import java.util.List;
import com.restaurante.proyecto.entities.Comida;

public interface ComidaService {
    
    List<Comida> obtenerTodos();

    Comida obtenerPorId(Long id);

    Comida crearComida(Comida comida);

    Comida actualizarComida(Long id, Comida comida);

    void eliminarComida(Long id);

    long contarComida();
}
