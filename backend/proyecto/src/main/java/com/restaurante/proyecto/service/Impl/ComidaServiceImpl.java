package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Comida;
import com.restaurante.proyecto.repository.ComidaRepository;
import com.restaurante.proyecto.service.ComidaService;

@Service
public class ComidaServiceImpl implements ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    @Override
    public List<Comida> obtenerTodos() {
        return comidaRepository.findAll();
    }

    @Override
    public Comida obtenerPorId(Long id) {
        return comidaRepository.findById(id).orElse(null);
    }

    @Override
    public Comida crearComida(Comida comida) {
        return comidaRepository.save(comida);
    }

    @Override
    public Comida actualizarComida(Long id, Comida comida) {
        Comida comidaBD = comidaRepository.findById(id).orElse(null);

        if (comidaBD != null) {
            comidaBD.setNombreComida(comida.getNombreComida());
            comidaBD.setTipoComida(comida.getTipoComida());
            comidaBD.setDescripcion(comida.getDescripcion());
            comidaBD.setPrecio(comida.getPrecio());
            comidaBD.setLocal(comida.getLocal());
            return comidaRepository.save(comidaBD);
        }
        return null;
    }

    @Override
    public void eliminarComida(Long id) {
        comidaRepository.deleteById(id);
    }

    @Override
    public long contarComida() {
        return comidaRepository.count();
    }
}
