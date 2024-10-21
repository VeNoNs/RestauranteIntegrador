package com.restaurante.proyecto.service;

import java.util.List;
import com.restaurante.proyecto.entities.Local;

public interface LocalService {
    
    List<Local> obtenerTodos();

    Local obtenerPorId(Long id);

    Local crearLocal(Local local);

    Local actualizarLocal(Long id, Local local);

    void eliminarLocal(Long id);

    long contarLocal();
}
