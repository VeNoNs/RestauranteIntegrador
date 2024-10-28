package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Pago;
import com.restaurante.proyecto.repository.PagoRepository;
import com.restaurante.proyecto.service.PagoService;

/**
 * Implementación del servicio para gestionar pagos.
 * Proporciona métodos para realizar operaciones CRUD en pagos.
 */
@Service
public class PagoServiceImpl implements PagoService {
    
    @Autowired
    private PagoRepository pagoRepository;

    /**
     * Obtiene todos los pagos.
     *
     * @return Lista de todos los pagos disponibles.
     */
    @Override
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    /**
     * Obtiene un pago por su ID.
     *
     * @param id El ID del pago a buscar.
     * @return El pago correspondiente al ID, o null si no se encuentra.
     */
    @Override
    public Pago obtenerPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo pago.
     *
     * @param pago El objeto Pago a crear.
     * @return El pago creado.
     */
    @Override
    public Pago crearPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    /**
     * Actualiza un pago existente.
     *
     * @param id El ID del pago a actualizar.
     * @param pago El objeto Pago con los nuevos datos.
     * @return El pago actualizado, o null si el pago no existe.
     */
    @Override
    public Pago actualizarPago(Long id, Pago pago) {
        Pago pagoBD = pagoRepository.findById(id).orElse(null);

        if (pagoBD != null) {
            pagoBD.setFechaPago(pago.getFechaPago());
            pagoBD.setMonto(pago.getMonto());
            return pagoRepository.save(pagoBD);
        }
        return null;
    }

    /**
     * Elimina un pago por su ID.
     *
     * @param id El ID del pago a eliminar.
     */
    @Override
    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }

    /**
     * Cuenta el número total de pagos.
     *
     * @return El número total de pagos.
     */
    @Override
    public long contarPago() {
        return pagoRepository.count();
    }
}
