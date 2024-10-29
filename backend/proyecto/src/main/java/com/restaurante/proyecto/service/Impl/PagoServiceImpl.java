package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Pago;
import com.restaurante.proyecto.repository.PagoRepository;
import com.restaurante.proyecto.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago obtenerPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @Override
    public Pago crearPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago actualizarPago(Long id, Pago pago) {
        Pago pagoBD = pagoRepository.findById(id).orElse(null);

        if (pagoBD != null) {
            pagoBD.setFechaPago(pago.getFechaPago());
            pagoBD.setMonto(pago.getMonto());
            pagoBD.setOrden(pago.getOrden());  // Asegúrate de actualizar la orden si se modifica
            return pagoRepository.save(pagoBD);
        }
        return null;
    }

    @Override
    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public long contarPago() {
        return pagoRepository.count();
    }
}
