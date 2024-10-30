package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.AdministradorRestaurante;
import java.util.List;
/**
 * Interfaz que define los servicios para la gestión de {@code AdministradorRestaurante}.
 */
public interface AdministradorRestauranteService {
    /**
     * Obtiene una lista de todos los administradores de restaurante.
     *
     * @return una lista de {@code AdministradorRestaurante}
     */
    List<AdministradorRestaurante> obtenerTodos();
    /**
     * Obtiene un administrador de restaurante por su identificador.
     *
     * @param id el identificador del administrador de restaurante
     * @return el administrador de restaurante correspondiente al identificador
     */
    AdministradorRestaurante obtenerPorId(Long id);
    /**
     * Crea un nuevo administrador de restaurante.
     *
     * @param adminRestaurante el administrador de restaurante a crear
     * @return el administrador de restaurante creado
     */
    AdministradorRestaurante crearAdministradorRestaurante(AdministradorRestaurante adminRestaurante);
    /**
     * Actualiza un administrador de restaurante existente.
     *
     * @param id el identificador del administrador a actualizar
     * @param adminRestaurante el administrador de restaurante con los nuevos datos
     * @return el administrador de restaurante actualizado
     */
    AdministradorRestaurante actualizarAdministradorRestaurante(Long id, AdministradorRestaurante adminRestaurante);
    /**
     * Elimina un administrador de restaurante por su identificador.
     *
     * @param id el identificador del administrador a eliminar
     */
    void eliminarAdministradorRestaurante(Long id);
    /**
     * Cuenta el número total de administradores de restaurante.
     *
     * @return el número total de administradores de restaurante
     */
    long contarAdministradorRestaurante();
}
