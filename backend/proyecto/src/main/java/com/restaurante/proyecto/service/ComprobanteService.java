package com.restaurante.proyecto.service;

import com.restaurante.proyecto.entities.Comprobante;
import java.util.List;

public interface ComprobanteService {

    List<Comprobante> obtenerTodos();

    Comprobante obtenerPorId(Long id);

    Comprobante crearComprobante(Comprobante comprobante);

    Comprobante actualizarComprobante(Long id, Comprobante comprobante);

    void eliminarComprobante(Long id);

    long contarComprobante();
}
