package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Comida;
import com.restaurante.proyecto.service.ComidaService;

/**
 * Implementación del servicio para manejar operaciones relacionadas con la entidad Comida.
 * Proporciona métodos para obtener, crear, actualizar y eliminar comidas.
 */
@Service
public class ComidaServiceImpl implements ComidaService {

    /**
     * Obtiene una lista de todas las comidas.
     *
     * @return Lista de objetos Comida.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public List<Comida> obtenerTodos() {
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodos'");
    }

    /**
     * Obtiene una comida por su ID.
     *
     * @param id El ID de la comida.
     * @return El objeto Comida correspondiente.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public Comida obtenerPorId(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    /**
     * Crea una nueva comida.
     *
     * @param comida El objeto Comida a crear.
     * @return El objeto Comida creado.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public Comida crearComida(Comida comida) {
        throw new UnsupportedOperationException("Unimplemented method 'crearComida'");
    }

    /**
     * Actualiza una comida existente.
     *
     * @param id El ID de la comida a actualizar.
     * @param comida El objeto Comida con la nueva información.
     * @return El objeto Comida actualizado.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public Comida actualizarComida(Long id, Comida comida) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizarComida'");
    }

    /**
     * Elimina una comida por su ID.
     *
     * @param id El ID de la comida a eliminar.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public void eliminarComida(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'eliminarComida'");
    }

    /**
     * Cuenta el número total de comidas.
     *
     * @return El número total de comidas.
     * @throws UnsupportedOperationException Si el método no está implementado.
     */
    @Override
    public long contarComida() {
        throw new UnsupportedOperationException("Unimplemented method 'contarComida'");
    }
}
