package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Comensal;
import com.restaurante.proyecto.repository.ComensalRepository;
import com.restaurante.proyecto.service.ComensalService;

@Service
public class ComensalServiceImpl implements ComensalService {
    
    @Autowired
    private ComensalRepository comensalRepository;

    @Override
    public List<Comensal> obtenerTodos() {
        return comensalRepository.findAll();
    }

    @Override
    public Comensal obtenerPorId(Long id) {
        return comensalRepository.findById(id).orElse(null);
    }

    @Override
    public Comensal crearComensal(Comensal comensal) {
        return comensalRepository.save(comensal);
    }

    @Override
    public Comensal actualizarComensal(Long id, Comensal comensal) {
        Comensal comensalBD = comensalRepository.findById(id).orElse(null);

        if (comensalBD != null) {
            comensalBD.setNombreComensal(comensal.getNombreComensal());
            comensalBD.setApellidoComensal(comensal.getApellidoComensal());
            comensalBD.setCorreo(comensal.getCorreo());
            return comensalRepository.save(comensalBD);
        }
        return null;
    }

    @Override
    public void eliminarComensal(Long id) {
        comensalRepository.deleteById(id);
    }

    @Override
    public long contarComensal() {
        return comensalRepository.count();
    }
}
