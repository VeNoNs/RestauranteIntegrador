package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Registro;
import com.restaurante.proyecto.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con los registros.
 */
@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    // 1. GET - Listar todos los registros
    @GetMapping("/listar")
    public List<Registro> listarRegistros() {
        return registroService.obtenerTodos();
    }

    // 2. GET - Obtener un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Registro> obtenerRegistro(@PathVariable Long id) {
        Registro registro = registroService.obtenerPorId(id);
        if (registro != null) {
            return ResponseEntity.ok(registro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. POST - Crear un nuevo registro
    @PostMapping("/crear")
    public ResponseEntity<Registro> crearRegistro(@RequestBody Registro registro) {
        Registro nuevoRegistro = registroService.crearRegistro(registro);
        return ResponseEntity.ok(nuevoRegistro);
    }

    // 4. PUT - Actualizar un registro existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Registro> actualizarRegistro(@PathVariable Long id, @RequestBody Registro registro) {
        Registro registroActualizado = registroService.actualizarRegistro(id, registro);
        if (registroActualizado != null) {
            return ResponseEntity.ok(registroActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE - Eliminar un registro
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        registroService.eliminarRegistro(id);
        return ResponseEntity.noContent().build();
    }
}
