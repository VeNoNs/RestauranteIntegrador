package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Reserva;
import com.restaurante.proyecto.repository.ReservaRepository;
import com.restaurante.proyecto.service.ReservaService;
/**
 * Clase de implementación del servicio {@code ReservaService}.
 */
@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> obtenerTodos() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva obtenerPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva actualizarReserva(Long id, Reserva reserva) {
        Reserva reservaBD = reservaRepository.findById(id).orElse(null);

        if (reservaBD != null) {
            reservaBD.setCantidadPersonas(reserva.getCantidadPersonas());
            reservaBD.setFechaReserva(reserva.getFechaReserva());
            reservaBD.setHoraReserva(reserva.getHoraReserva());
            reservaBD.setMesa(reserva.getMesa());
            return reservaRepository.save(reservaBD);
        }
        return null;
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public long contarReserva() {
        return reservaRepository.count();
    }
}
