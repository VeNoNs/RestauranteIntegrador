package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.Reserva;
import java.util.List;

public interface ReservaService {

    List<Reserva> obtenerTodos();

    Reserva obtenerPorId(Long id);

    Reserva crearReserva(Reserva reserva);

    Reserva actualizarReserva(Long id, Reserva reserva);

    void eliminarReserva(Long id);

    long contarReserva();
}
