package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Orden;
import com.restaurante.proyecto.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verordenes")
    @ResponseBody
    public List<Orden> listarOrdenes() {
        return ordenService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<Orden> guardarNuevaOrden(@RequestBody Orden orden) {
        Orden nuevaOrden = ordenService.crearOrden(orden);
        return ResponseEntity.ok(nuevaOrden);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idOrden}")
    @ResponseBody
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long idOrden, @RequestBody Orden orden) {
        Orden ordenActualizada = ordenService.actualizarOrden(idOrden, orden);
        if (ordenActualizada != null) {
            return ResponseEntity.ok(ordenActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idOrden}")
    @ResponseBody
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long idOrden) {
        ordenService.eliminarOrden(idOrden);
        return ResponseEntity.noContent().build();
    }
}
