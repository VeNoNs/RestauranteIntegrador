package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import java.util.List;
/**
 * Interfaz que define los servicios para la gestión de {@code AdministradorEmpresa}.
 */
public interface AdministradorEmpresaService {
    /**
     * Obtiene una lista de todos los administradores de empresa.
     *
     * @return una lista de {@code AdministradorEmpresa}
     */
    List<AdministradorEmpresa> obtenerTodos();
    /**
     * Obtiene un administrador de empresa por su identificador.
     *
     * @param id el identificador del administrador de empresa
     * @return el administrador de empresa correspondiente al identificador
     */
    AdministradorEmpresa obtenerPorId(Long id);
    /**
     * Crea un nuevo administrador general.
     *
     * @param adminGeneral el administrador general a crear
     * @return el administrador general creado
     */
    AdministradorEmpresa crearAdministradorGeneral(AdministradorEmpresa adminGeneral);
    /**
     * Actualiza un administrador general existente.
     *
     * @param id el identificador del administrador a actualizar
     * @param adminGeneral el administrador general con los nuevos datos
     * @return el administrador general actualizado
     */
    AdministradorEmpresa actualizarAdministradorGeneral(Long id, AdministradorEmpresa adminGeneral);
    /**
     * Elimina un administrador general por su identificador.
     *
     * @param id el identificador del administrador a eliminar
     */
    void eliminarAdministradorGeneral(Long id);
    /**
     * Cuenta el número total de administradores generales.
     *
     * @return el número total de administradores generales
     */
    long contarAdministradorGeneral();
}
