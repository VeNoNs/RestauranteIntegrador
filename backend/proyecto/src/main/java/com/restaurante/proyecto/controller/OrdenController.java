package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Orden;
import com.restaurante.proyecto.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * Controlador para manejar las operaciones relacionadas con las ordenes.
 */
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

    // Modificar el estado de una orden existente
// Modificar el estado de una orden existente
@PutMapping("/api/editar/{idOrden}")
@ResponseBody
public ResponseEntity<Orden> actualizarEstadoOrden(@PathVariable Long idOrden, @RequestBody Map<String, String> estadoMap) {
    Orden ordenExistente = ordenService.obtenerPorId(idOrden);
    if (ordenExistente != null) {
        // Cambiar el estado de la orden usando el valor del mapa
        String nuevoEstado = estadoMap.get("estado");
        ordenExistente.setEstado(nuevoEstado);
        Orden ordenActualizada = ordenService.actualizarOrden(idOrden, ordenExistente);
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
    
    @GetMapping("/api/verorden/{idOrden}")
@ResponseBody
public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable Long idOrden) {
    Orden orden = ordenService.obtenerPorId(idOrden);
    if (orden != null) {
        return ResponseEntity.ok(orden);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
