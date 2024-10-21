package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Comida;
import com.restaurante.proyecto.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comida")
public class ComidaController {

    @Autowired
    private ComidaService comidaService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/vercomidas")
    @ResponseBody
    public List<Comida> listarComidas() {
        return comidaService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<Comida> guardarNuevaComida(@RequestBody Comida comida) {
        Comida nuevaComida = comidaService.crearComida(comida);
        return ResponseEntity.ok(nuevaComida);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idComida}")
    @ResponseBody
    public ResponseEntity<Comida> actualizarComida(@PathVariable Long idComida, @RequestBody Comida comida) {
        Comida comidaActualizada = comidaService.actualizarComida(idComida, comida);
        if (comidaActualizada != null) {
            return ResponseEntity.ok(comidaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idComida}")
    @ResponseBody
    public ResponseEntity<Void> eliminarComida(@PathVariable Long idComida) {
        comidaService.eliminarComida(idComida);
        return ResponseEntity.noContent().build();
    }
}
