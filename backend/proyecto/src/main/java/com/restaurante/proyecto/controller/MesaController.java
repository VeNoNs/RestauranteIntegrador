package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Mesa;
import com.restaurante.proyecto.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    // 1. GET - Listar todas las mesas
    @GetMapping("/ver")
    public List<Mesa> listarMesas() {
        return mesaService.obtenerTodos();
    }

    // 2. POST - Crear una nueva mesa
    @PostMapping("/nueva")
    public ResponseEntity<Mesa> crearMesa(@RequestBody Mesa mesa) {
        Mesa nuevaMesa = mesaService.crearMesa(mesa);
        return ResponseEntity.ok(nuevaMesa);
    }

    // 3. GET - Obtener una mesa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obtenerMesaPorId(@PathVariable Long id) {
        Mesa mesa = mesaService.obtenerPorId(id);
        if (mesa != null) {
            return ResponseEntity.ok(mesa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. PUT - Actualizar una mesa existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<Mesa> actualizarMesa(@PathVariable Long id, @RequestBody Mesa mesa) {
        Mesa mesaActualizada = mesaService.actualizarMesa(id, mesa);
        if (mesaActualizada != null) {
            return ResponseEntity.ok(mesaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE - Eliminar una mesa
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMesa(@PathVariable Long id) {
        mesaService.eliminarMesa(id);
        return ResponseEntity.noContent().build();
    }
}
