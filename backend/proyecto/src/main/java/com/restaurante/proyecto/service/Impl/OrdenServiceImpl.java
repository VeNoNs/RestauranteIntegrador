package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Orden;
import com.restaurante.proyecto.repository.OrdenRepository;
import com.restaurante.proyecto.service.OrdenService;
/**
 * Clase de implementación del servicio {@code OrdenService}.
 */
@Service
public class OrdenServiceImpl implements OrdenService {
    
    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public List<Orden> obtenerTodos() {
        return ordenRepository.findAll();
    }

    @Override
    public Orden obtenerPorId(Long id) {
        return ordenRepository.findById(id).orElse(null);
    }

    @Override
    public Orden crearOrden(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public Orden actualizarOrden(Long id, Orden orden) {
        Orden ordenBD = ordenRepository.findById(id).orElse(null);

        if (ordenBD != null) {
            ordenBD.setCantidad(orden.getCantidad());
            ordenBD.setSubTotal(orden.getSubTotal());
            ordenBD.setComida(orden.getComida());
            ordenBD.setMesa(orden.getMesa());  // Asegúrate de actualizar también la mesa
            return ordenRepository.save(ordenBD);
        }
        return null;
    }

    @Override
    public void eliminarOrden(Long id) {
        ordenRepository.deleteById(id);
    }

    @Override
    public long contarOrden() {
        return ordenRepository.count();
    }
}
