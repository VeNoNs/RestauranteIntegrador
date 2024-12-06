package com.restaurante.proyecto.service;

import java.util.List;
import com.restaurante.proyecto.entities.Pago;
/**
 * Interfaz que define los servicios para la gestión de {@code Pago}.
 */
public interface PagoService {
    
    List<Pago> obtenerTodos();

    Pago obtenerPorId(Long id);

    Pago crearPago(Pago pago);

    Pago actualizarPago(Long id, Pago pago);

    void eliminarPago(Long id);

    long contarPago();
}
