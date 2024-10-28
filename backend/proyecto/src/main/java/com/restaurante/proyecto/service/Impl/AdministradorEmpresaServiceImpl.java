package com.restaurante.proyecto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.repository.AdministradorEmpresaRepository;
import com.restaurante.proyecto.service.AdministradorEmpresaService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para manejar operaciones relacionadas con la entidad AdministradorEmpresa.
 * Proporciona métodos para obtener, crear, actualizar y eliminar administradores de empresa.
 */
@Service
public class AdministradorEmpresaServiceImpl implements AdministradorEmpresaService {

    @Autowired
    private AdministradorEmpresaRepository empresaRepository;

    /**
     * Obtiene una lista de todos los administradores de empresa.
     *
     * @return Lista de objetos AdministradorEmpresa.
     */
    @Override
    public List<AdministradorEmpresa> obtenerTodos() {
        return empresaRepository.findAll();
    }

    /**
     * Obtiene un administrador de empresa por su ID.
     *
     * @param id El ID del administrador de empresa.
     * @return El objeto AdministradorEmpresa correspondiente o null si no se encuentra.
     */
    @Override
    public AdministradorEmpresa obtenerPorId(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo administrador de empresa.
     *
     * @param empresa El objeto AdministradorEmpresa a crear.
     * @return El objeto AdministradorEmpresa creado.
     */
    @Override
    public AdministradorEmpresa crearEmpresa(AdministradorEmpresa empresa) {
        return empresaRepository.save(empresa);
    }

    /**
     * Actualiza un administrador de empresa existente.
     *
     * @param id El ID del administrador de empresa a actualizar.
     * @param empresa El objeto AdministradorEmpresa con la nueva información.
     * @return El objeto AdministradorEmpresa actualizado o null si no se encuentra el administrador.
     */
    @Override
    public AdministradorEmpresa actualizarEmpresa(Long id, AdministradorEmpresa empresa) {
        AdministradorEmpresa empresaBD = empresaRepository.findById(id).orElse(null);

        if (empresaBD != null) {
            empresaBD.setNombreEmpresa(empresa.getNombreEmpresa());
            empresaBD.setTelefono(empresa.getTelefono());
            empresaBD.setUsuario(empresa.getUsuario());
            return empresaRepository.save(empresaBD);
        }
        return null;
    }

    /**
     * Elimina un administrador de empresa por su ID.
     *
     * @param id El ID del administrador de empresa a eliminar.
     */
    @Override
    public void eliminarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    /**
     * Cuenta el número total de administradores de empresa.
     *
     * @return El número total de administradores de empresa.
     */
    @Override
    public long contarEmpresa() {
        return empresaRepository.count();
    }
}
