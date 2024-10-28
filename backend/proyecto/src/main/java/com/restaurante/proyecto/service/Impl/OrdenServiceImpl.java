package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Orden;
import com.restaurante.proyecto.repository.OrdenRepository;
import com.restaurante.proyecto.service.OrdenService;

/**
 * Implementación del servicio para gestionar órdenes.
 * Proporciona métodos para realizar operaciones CRUD en órdenes.
 */
@Service
public class OrdenServiceImpl implements OrdenService {
    
    @Autowired
    private OrdenRepository ordenRepository;

    /**
     * Obtiene todas las órdenes.
     *
     * @return Lista de todas las órdenes disponibles.
     */
    @Override
    public List<Orden> obtenerTodos() {
        return ordenRepository.findAll();
    }

    /**
     * Obtiene una orden por su ID.
     *
     * @param id El ID de la orden a buscar.
     * @return La orden correspondiente al ID, o null si no se encuentra.
     */
    @Override
    public Orden obtenerPorId(Long id) {
        return ordenRepository.findById(id).orElse(null);
    }

    /**
     * Crea una nueva orden.
     *
     * @param orden El objeto Orden a crear.
     * @return La orden creada.
     */
    @Override
    public Orden crearOrden(Orden orden) {
        return ordenRepository.save(orden);
    }

    /**
     * Actualiza una orden existente.
     *
     * @param id El ID de la orden a actualizar.
     * @param orden El objeto Orden con los nuevos datos.
     * @return La orden actualizada, o null si la orden no existe.
     */
    @Override
    public Orden actualizarOrden(Long id, Orden orden) {
        Orden ordenBD = ordenRepository.findById(id).orElse(null);

        if (ordenBD != null) {
            ordenBD.setCantidad(orden.getCantidad());
            ordenBD.setSubTotal(orden.getSubTotal());
            ordenBD.setComida(orden.getComida());
            return ordenRepository.save(ordenBD);
        }
        return null;
    }

    /**
     * Elimina una orden por su ID.
     *
     * @param id El ID de la orden a eliminar.
     */
    @Override
    public void eliminarOrden(Long id) {
        ordenRepository.deleteById(id);
    }

    /**
     * Cuenta el número total de órdenes.
     *
     * @return El número total de órdenes.
     */
    @Override
    public long contarOrden() {
        return ordenRepository.count();
    }
}
