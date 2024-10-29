package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.Comensal;
import java.util.List;

public interface ComensalService {

    List<Comensal> obtenerTodos();

    Comensal obtenerPorId(Long id);

    Comensal crearComensal(Comensal comensal);

    Comensal actualizarComensal(Long id, Comensal comensal);

    void eliminarComensal(Long id);

    long contarComensal();
}
