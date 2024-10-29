package com.restaurante.proyecto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.repository.AdministradorEmpresaRepository;
import com.restaurante.proyecto.service.AdministradorEmpresaService;
import org.springframework.stereotype.Service;

@Service
public class AdministradorEmpresaServiceImpl implements AdministradorEmpresaService {
    
    @Autowired
    private AdministradorEmpresaRepository adminGeneralRepository;

    @Override
    public List<AdministradorEmpresa> obtenerTodos(){
        return adminGeneralRepository.findAll();
    }

    @Override
    public AdministradorEmpresa obtenerPorId(Long id){
        return adminGeneralRepository.findById(id).orElse(null);
    }

    @Override
    public AdministradorEmpresa crearAdministradorGeneral(AdministradorEmpresa adminGeneral){
        return adminGeneralRepository.save(adminGeneral);
    }

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

    @Override
    public void eliminarAdministradorGeneral(Long id) {
        adminGeneralRepository.deleteById(id);
    }

    @Override
    public long contarAdministradorGeneral() {
        return adminGeneralRepository.count();
    }
}
