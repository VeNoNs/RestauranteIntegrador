package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.Mesa;
import java.util.List;

public interface MesaService {
    
    List<Mesa> obtenerTodos();
    
    Mesa obtenerPorId(Long id);
    
    Mesa crearMesa(Mesa mesa);
    
    Mesa actualizarMesa(Long id, Mesa mesa);
    
    void eliminarMesa(Long id);
    
    long contarMesas();
}
