package com.restaurante.proyecto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.repository.*;
import com.restaurante.proyecto.service.*;
import org.springframework.stereotype.Service;

@Service
public class AdministradorEmpresaServiceImpl implements AdministradorEmpresaService{
    
    @Autowired
    private AdministradorEmpresaRepository empresaRepository;

    @Override
    public List<AdministradorEmpresa> obtenerTodos(){
        return empresaRepository.findAll();
    }

    @Override
    public AdministradorEmpresa obtenerPorId(Long id){
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public AdministradorEmpresa crearEmpresa(AdministradorEmpresa empresa){
        return empresaRepository.save(empresa);
    }

    @Override
    public AdministradorEmpresa actualizarEmpresa(Long id, AdministradorEmpresa empresa){
        AdministradorEmpresa empresaBD = empresaRepository.findById(id).orElse(null);

        if(empresaBD != null){
            empresaBD.setNombreEmpresa(empresa.getNombreEmpresa());
            empresaBD.setTelefono(empresa.getTelefono());
            empresaBD.setUsuario(empresa.getUsuario());
            return empresaRepository.save(empresaBD);
        }
        return null;
    }

    @Override
    public void eliminarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public long contarEmpresa() {
        return empresaRepository.count();
    }
}
