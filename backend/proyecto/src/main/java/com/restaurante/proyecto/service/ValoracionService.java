package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.Valoracion;
import java.util.List;
/**
 * Interfaz que define los servicios para la gestión de {@code Valoracion}.
 */
public interface ValoracionService {

    List<Valoracion> obtenerTodos();

    Valoracion obtenerPorId(Long id);

    Valoracion crearValoracion(Valoracion valoracion);

    Valoracion actualizarValoracion(Long id, Valoracion valoracion);

    void eliminarValoracion(Long id);

    long contarValoracion();
}
