package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Local;
import com.restaurante.proyecto.repository.LocalRepository;
import com.restaurante.proyecto.service.LocalService;

@Service
public class LocalServiceImpl implements LocalService {
    
    @Autowired
    private LocalRepository localRepository;

    @Override
    public List<Local> obtenerTodos() {
        return localRepository.findAll();
    }

    @Override
    public Local obtenerPorId(Long id) {
        return localRepository.findById(id).orElse(null);
    }

    @Override
    public Local crearLocal(Local local) {
        return localRepository.save(local);
    }

    @Override
    public Local actualizarLocal(Long id, Local local) {
        Local localBD = localRepository.findById(id).orElse(null);

        if (localBD != null) {
            localBD.setNroMesa(local.getNroMesa());
            return localRepository.save(localBD);
        }
        return null;
    }

    @Override
    public void eliminarLocal(Long id) {
        localRepository.deleteById(id);
    }

    @Override
    public long contarLocal() {
        return localRepository.count();
    }
}
