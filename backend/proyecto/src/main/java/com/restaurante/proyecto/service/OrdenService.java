package com.restaurante.proyecto.service;

import java.util.List;
import com.restaurante.proyecto.entities.Orden;

public interface OrdenService {
    
    List<Orden> obtenerTodos();

    Orden obtenerPorId(Long id);

    Orden crearOrden(Orden orden);

    Orden actualizarOrden(Long id, Orden orden);

    void eliminarOrden(Long id);

    long contarOrden();
}
