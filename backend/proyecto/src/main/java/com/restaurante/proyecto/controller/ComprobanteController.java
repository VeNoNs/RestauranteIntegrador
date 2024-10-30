package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Comprobante;
import com.restaurante.proyecto.service.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con los comprobantes.
 */
@RestController
@RequestMapping("/api/comprobante")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    // Obtener todos los comprobantes
    @GetMapping("/todos")
    public List<Comprobante> obtenerComprobantes() {
        return comprobanteService.obtenerTodos();
    }

    // Obtener un comprobante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comprobante> obtenerComprobantePorId(@PathVariable Long id) {
        Comprobante comprobante = comprobanteService.obtenerPorId(id);
        if (comprobante != null) {
            return ResponseEntity.ok(comprobante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo comprobante
    @PostMapping("/crear")
    public ResponseEntity<Comprobante> crearComprobante(@RequestBody Comprobante comprobante) {
        Comprobante nuevoComprobante = comprobanteService.crearComprobante(comprobante);
        return ResponseEntity.ok(nuevoComprobante);
    }

    // Actualizar un comprobante existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Comprobante> actualizarComprobante(@PathVariable Long id, @RequestBody Comprobante comprobante) {
        Comprobante comprobanteActualizado = comprobanteService.actualizarComprobante(id, comprobante);
        if (comprobanteActualizado != null) {
            return ResponseEntity.ok(comprobanteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un comprobante
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarComprobante(@PathVariable Long id) {
        comprobanteService.eliminarComprobante(id);
        return ResponseEntity.noContent().build();
    }
}
