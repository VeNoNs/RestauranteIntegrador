package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Valoracion;
import com.restaurante.proyecto.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con las valoraciones.
 */
@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    // 1. GET - Listar todas las valoraciones
    @GetMapping("/listar")
    public List<Valoracion> listarValoraciones() {
        return valoracionService.obtenerTodos();
    }

    // 2. GET - Obtener una valoracion por ID
    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> obtenerValoracion(@PathVariable Long id) {
        Valoracion valoracion = valoracionService.obtenerPorId(id);
        if (valoracion != null) {
            return ResponseEntity.ok(valoracion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. POST - Crear una nueva valoracion
    @PostMapping("/crear")
    public ResponseEntity<Valoracion> crearValoracion(@RequestBody Valoracion valoracion) {
        Valoracion nuevaValoracion = valoracionService.crearValoracion(valoracion);
        return ResponseEntity.ok(nuevaValoracion);
    }

    // 4. PUT - Actualizar una valoracion existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Valoracion> actualizarValoracion(@PathVariable Long id, @RequestBody Valoracion valoracion) {
        Valoracion valoracionActualizada = valoracionService.actualizarValoracion(id, valoracion);
        if (valoracionActualizada != null) {
            return ResponseEntity.ok(valoracionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE - Eliminar una valoracion
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarValoracion(@PathVariable Long id) {
        valoracionService.eliminarValoracion(id);
        return ResponseEntity.noContent().build();
    }
}
