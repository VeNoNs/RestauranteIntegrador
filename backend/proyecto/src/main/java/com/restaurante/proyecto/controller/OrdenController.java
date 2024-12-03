package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Orden;
import com.restaurante.proyecto.service.OrdenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orden")
public class OrdenController {

    private static final Logger logger = LoggerFactory.getLogger(OrdenController.class);

    @Autowired
    private OrdenService ordenService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verordenes")
    @ResponseBody
    public List<Orden> listarOrdenes() {
        logger.info("Solicitud recibida: Listar todas las órdenes.");
        List<Orden> ordenes = ordenService.obtenerTodos();
        logger.info("Órdenes encontradas: {} registros.", ordenes.size());
        return ordenes;
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<Orden> guardarNuevaOrden(@RequestBody Orden orden) {
        logger.info("Solicitud recibida: Crear una nueva orden para el cliente con ID '{}'.", orden.getClienteId());
        try {
            Orden nuevaOrden = ordenService.crearOrden(orden);
            logger.info("Nueva orden creada exitosamente con ID {}.", nuevaOrden.getIdOrden());
            return ResponseEntity.ok(nuevaOrden);
        } catch (Exception e) {
            logger.error("Error al crear la nueva orden: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idOrden}")
    @ResponseBody
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long idOrden, @RequestBody Orden orden) {
        logger.info("Solicitud recibida: Actualizar la orden con ID {}.", idOrden);
        try {
            Orden ordenActualizada = ordenService.actualizarOrden(idOrden, orden);
            if (ordenActualizada != null) {
                logger.info("Orden actualizada exitosamente con ID {}.", idOrden);
                return ResponseEntity.ok(ordenActualizada);
            } else {
                logger.warn("No se encontró una orden con ID {} para actualizar.", idOrden);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al actualizar la orden con ID {}: {}", idOrden, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idOrden}")
    @ResponseBody
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long idOrden) {
        logger.info("Solicitud recibida: Eliminar la orden con ID {}.", idOrden);
        try {
            ordenService.eliminarOrden(idOrden);
            logger.info("Orden eliminada exitosamente con ID {}.", idOrden);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar la orden con ID {}: {}", idOrden, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}

