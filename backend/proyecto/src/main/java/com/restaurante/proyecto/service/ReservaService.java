package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.Reserva;
import java.util.List;
/**
 * Interfaz que define los servicios para la gestión de {@code Reserva}.
 */
public interface ReservaService {

    List<Reserva> obtenerTodos();

    Reserva obtenerPorId(Long id);

    Reserva crearReserva(Reserva reserva);

    Reserva actualizarReserva(Long id, Reserva reserva);

    void eliminarReserva(Long id);

    long contarReserva();
}
