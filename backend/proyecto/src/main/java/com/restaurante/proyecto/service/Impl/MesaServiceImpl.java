package com.restaurante.proyecto.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Mesa;
import com.restaurante.proyecto.repository.MesaRepository;
import com.restaurante.proyecto.service.MesaService;

@Service
public class MesaServiceImpl implements MesaService {
    
    @Autowired
    private MesaRepository mesaRepository;

    @Override
    public List<Mesa> obtenerTodos() {
        return mesaRepository.findAll();
    }

    @Override
    public Mesa obtenerPorId(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }

    @Override
    public Mesa crearMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @Override
    public Mesa actualizarMesa(Long id, Mesa mesa) {
        Mesa mesaBD = mesaRepository.findById(id).orElse(null);
        
        if (mesaBD != null) {
            mesaBD.setNroMesa(mesa.getNroMesa());
            mesaBD.setCantidadSillas(mesa.getCantidadSillas());
            mesaBD.setEstado(mesa.getEstado());
            mesaBD.setLocal(mesa.getLocal());
            return mesaRepository.save(mesaBD);
        }
        return null;
    }

    @Override
    public void eliminarMesa(Long id) {
        mesaRepository.deleteById(id);
    }

    @Override
    public long contarMesas() {
        return mesaRepository.count();
    }
}
