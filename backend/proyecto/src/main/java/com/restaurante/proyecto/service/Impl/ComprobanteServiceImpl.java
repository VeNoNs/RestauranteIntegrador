package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Comprobante;
import com.restaurante.proyecto.repository.ComprobanteRepository;
import com.restaurante.proyecto.service.ComprobanteService;
/**
 * Clase de implementación del servicio {@code ComprobanteService}.
 */
@Service
public class ComprobanteServiceImpl implements ComprobanteService {
    
    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Override
    public List<Comprobante> obtenerTodos() {
        return comprobanteRepository.findAll();
    }

    @Override
    public Comprobante obtenerPorId(Long id) {
        return comprobanteRepository.findById(id).orElse(null);
    }

    @Override
    public Comprobante crearComprobante(Comprobante comprobante) {
        return comprobanteRepository.save(comprobante);
    }

    @Override
    public Comprobante actualizarComprobante(Long id, Comprobante comprobante) {
        Comprobante comprobanteBD = comprobanteRepository.findById(id).orElse(null);

        if (comprobanteBD != null) {
            comprobanteBD.setFechaComprobante(comprobante.getFechaComprobante());
            comprobanteBD.setPago(comprobante.getPago());
            return comprobanteRepository.save(comprobanteBD);
        }
        return null;
    }

    @Override
    public void eliminarComprobante(Long id) {
        comprobanteRepository.deleteById(id);
    }

    @Override
    public long contarComprobante() {
        return comprobanteRepository.count();
    }
}
