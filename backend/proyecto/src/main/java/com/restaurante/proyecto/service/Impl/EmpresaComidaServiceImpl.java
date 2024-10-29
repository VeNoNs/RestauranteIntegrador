package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.EmpresaComida;
import com.restaurante.proyecto.repository.EmpresaComidaRepository;
import com.restaurante.proyecto.service.EmpresaComidaService;

@Service
public class EmpresaComidaServiceImpl implements EmpresaComidaService {
    
    @Autowired
    private EmpresaComidaRepository empresaComidaRepository;

    @Override
    public List<EmpresaComida> obtenerTodos() {
        return empresaComidaRepository.findAll();
    }

    @Override
    public EmpresaComida obtenerPorId(Long id) {
        return empresaComidaRepository.findById(id).orElse(null);
    }

    @Override
    public EmpresaComida crearEmpresa(EmpresaComida empresa) {
        return empresaComidaRepository.save(empresa);
    }

    @Override
    public EmpresaComida actualizarEmpresa(Long id, EmpresaComida empresa) {
        EmpresaComida empresaBD = empresaComidaRepository.findById(id).orElse(null);

        if (empresaBD != null) {
            // Actualizar los atributos que se permiten modificar
            empresaBD.setNombreEmpresa(empresa.getNombreEmpresa());
            empresaBD.setTelefono(empresa.getTelefono());
            return empresaComidaRepository.save(empresaBD);
        }
        return null;
    }

    @Override
    public void eliminarEmpresa(Long id) {
        empresaComidaRepository.deleteById(id);
    }

    @Override
    public long contarEmpresa() {
        return empresaComidaRepository.count();
    }
}
