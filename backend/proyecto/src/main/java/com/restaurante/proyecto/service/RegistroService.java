package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.Registro;
import java.util.List;
/**
 * Interfaz que define los servicios para la gestión de {@code Registros}.
 */
public interface RegistroService {
    
    List<Registro> obtenerTodos();
    
    Registro obtenerPorId(Long id);
    
    Registro crearRegistro(Registro registro);
    
    Registro actualizarRegistro(Long id, Registro registro);
    
    void eliminarRegistro(Long id);
    
    long contarRegistro();
}
