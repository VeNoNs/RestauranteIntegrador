package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Registro;
import com.restaurante.proyecto.repository.RegistroRepository;
import com.restaurante.proyecto.service.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService {
    
    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public List<Registro> obtenerTodos() {
        return registroRepository.findAll();
    }

    @Override
    public Registro obtenerPorId(Long id) {
        return registroRepository.findById(id).orElse(null);
    }

    @Override
    public Registro crearRegistro(Registro registro) {
        return registroRepository.save(registro);
    }

    @Override
    public Registro actualizarRegistro(Long id, Registro registro) {
        Registro registroBD = registroRepository.findById(id).orElse(null);
        if (registroBD != null) {
            registroBD.setFechaRegistro(registro.getFechaRegistro());
            registroBD.setComensal(registro.getComensal());
            return registroRepository.save(registroBD);
        }
        return null;
    }

    @Override
    public void eliminarRegistro(Long id) {
        registroRepository.deleteById(id);
    }

    @Override
    public long contarRegistro() {
        return registroRepository.count();
    }
}
