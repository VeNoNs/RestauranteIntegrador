package com.restaurante.proyecto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurante.proyecto.entities.Empresa;
import com.restaurante.proyecto.repository.*;
import com.restaurante.proyecto.service.*;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService{
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> obtenerTodos(){
        return empresaRepository.findAll();
    }

    @Override
    public Empresa obtenerPorId(Long id){
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public Empresa crearEmpresa(Empresa empresa){
        empresa = new Empresa(null, "nombreEmpresa","990204101","user1","contraseña1");
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa actualizarEmpresa(Long id, Empresa empresa){
        Empresa empresaBD = empresaRepository.findById(id).orElse(null);

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
