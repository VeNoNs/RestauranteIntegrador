package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Comensal;
import com.restaurante.proyecto.service.ComensalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con los comensales.
 */
@RestController
@RequestMapping("/api/comensales")
public class ComensalController {

    @Autowired
    private ComensalService comensalService;

    // 1. GET - Listar todos los comensales
    @GetMapping("/listar")
    public List<Comensal> listarComensales() {
        return comensalService.obtenerTodos();
    }

    // 2. GET - Obtener un comensal por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comensal> obtenerComensal(@PathVariable Long id) {
        Comensal comensal = comensalService.obtenerPorId(id);
        if (comensal != null) {
            return ResponseEntity.ok(comensal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. POST - Crear un nuevo comensal
    @PostMapping("/crear")
    public ResponseEntity<Comensal> crearComensal(@RequestBody Comensal comensal) {
        Comensal nuevoComensal = comensalService.crearComensal(comensal);
        return ResponseEntity.ok(nuevoComensal);
    }

    // 4. PUT - Actualizar un comensal existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Comensal> actualizarComensal(@PathVariable Long id, @RequestBody Comensal comensal) {
        Comensal comensalActualizado = comensalService.actualizarComensal(id, comensal);
        if (comensalActualizado != null) {
            return ResponseEntity.ok(comensalActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE - Eliminar un comensal
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarComensal(@PathVariable Long id) {
        comensalService.eliminarComensal(id);
        return ResponseEntity.noContent().build();
    }
}
