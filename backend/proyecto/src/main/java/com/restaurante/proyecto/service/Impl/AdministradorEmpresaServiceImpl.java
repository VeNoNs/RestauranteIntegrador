package com.restaurante.proyecto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.repository.AdministradorEmpresaRepository;
import com.restaurante.proyecto.service.AdministradorEmpresaService;
import org.springframework.stereotype.Service;
/**
 * Clase de implementación del servicio {@code AdministradorEmpresaService}.
 */
@Service
public class AdministradorEmpresaServiceImpl implements AdministradorEmpresaService {

    @Autowired
    private AdministradorEmpresaRepository adminGeneralRepository;
    /**
     * Obtiene una lista de todos los administradores de empresa.
     *
     * @return una lista de {@code AdministradorEmpresa}
     */
    @Override
    public List<AdministradorEmpresa> obtenerTodos(){
        return adminGeneralRepository.findAll();
    }
    /**
     * Obtiene un administrador de empresa por su identificador.
     *
     * @param id el identificador del administrador de empresa
     * @return el administrador de empresa correspondiente al identificador, o {@code null} si no existe
     */
    @Override
    public AdministradorEmpresa obtenerPorId(Long id){
        return adminGeneralRepository.findById(id).orElse(null);
    }
    /**
     * Crea un nuevo administrador de empresa.
     *
     * @param adminGeneral el administrador de empresa a crear
     * @return el administrador de empresa creado
     */
    @Override
    public AdministradorEmpresa crearAdministradorGeneral(AdministradorEmpresa adminGeneral){
        return adminGeneralRepository.save(adminGeneral);
    }
    /**
     * Actualiza un administrador de empresa existente.
     *
     * @param id el identificador del administrador a actualizar
     * @param adminGeneral los nuevos datos del administrador de empresa
     * @return el administrador de empresa actualizado, o {@code null} si no se encuentra
     */
    @Override
    public AdministradorEmpresa actualizarAdministradorGeneral(Long id, AdministradorEmpresa adminGeneral){
        AdministradorEmpresa adminGeneralBD = adminGeneralRepository.findById(id).orElse(null);

        if(adminGeneralBD != null){
            adminGeneralBD.setNombrePersona(adminGeneral.getNombrePersona());
            adminGeneralBD.setTelefono(adminGeneral.getTelefono());
            adminGeneralBD.setUsuario(adminGeneral.getUsuario());
            adminGeneralBD.setPassword(adminGeneral.getPassword());
            return adminGeneralRepository.save(adminGeneralBD);
        }
        return null;
    }
    /**
     * Elimina un administrador de empresa por su identificador.
     *
     * @param id el identificador del administrador a eliminar
     */
    @Override
    public void eliminarAdministradorGeneral(Long id) {
        adminGeneralRepository.deleteById(id);
    }
    /**
     * Cuenta el número total de administradores de empresa.
     *
     * @return el número total de administradores de empresa
     */
    @Override
    public long contarAdministradorGeneral() {
        return adminGeneralRepository.count();
    }
}
