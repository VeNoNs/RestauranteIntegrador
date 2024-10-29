package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Valoracion;
import com.restaurante.proyecto.repository.ValoracionRepository;
import com.restaurante.proyecto.service.ValoracionService;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public List<Valoracion> obtenerTodos() {
        return valoracionRepository.findAll();
    }

    @Override
    public Valoracion obtenerPorId(Long id) {
        return valoracionRepository.findById(id).orElse(null);
    }

    @Override
    public Valoracion crearValoracion(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Override
    public Valoracion actualizarValoracion(Long id, Valoracion valoracion) {
        Valoracion valoracionBD = valoracionRepository.findById(id).orElse(null);
        if (valoracionBD != null) {
            valoracionBD.setReseña(valoracion.getReseña());
            valoracionBD.setComensal(valoracion.getComensal());
            return valoracionRepository.save(valoracionBD);
        }
        return null;
    }

    @Override
    public void eliminarValoracion(Long id) {
        valoracionRepository.deleteById(id);
    }

    @Override
    public long contarValoracion() {
        return valoracionRepository.count();
    }
}
