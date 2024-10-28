package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Orden;
import com.restaurante.proyecto.service.OrdenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con la entidad Orden.
 * Proporciona endpoints para listar, crear, actualizar y eliminar órdenes.
 */
@Controller
@RequestMapping("/orden")
public class OrdenController {

    private static final Logger logger = LoggerFactory.getLogger(OrdenController.class); // Configuración de Logback

    @Autowired
    private OrdenService ordenService;

    /**
     * Endpoint para listar todas las órdenes.
     *
     * @return ResponseEntity con la lista de órdenes y un estado HTTP 200 (OK).
     */
    @GetMapping("/api/verordenes")
    @ResponseBody
    public ResponseEntity<List<Orden>> listarOrdenes() {
        logger.info("Listando todas las órdenes."); // Registro de información
        List<Orden> ordenes = ordenService.obtenerTodos();
        return ResponseEntity.ok(ordenes);
    }

    /**
     * Endpoint para crear una nueva orden.
     *
     * @param orden La orden a crear.
     * @return ResponseEntity con la orden creada y un estado HTTP 200 (OK).
     * @throws NullPointerException si la orden proporcionada es nula.
     */
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<Orden> guardarNuevaOrden(@RequestBody Orden orden) {
        // Validar que la orden no sea nula
        Preconditions.checkNotNull(orden, "La orden no puede ser nula.");
        
        Orden nuevaOrden = ordenService.crearOrden(orden);
        logger.info("Orden creada: ID={}, Detalle={}", nuevaOrden.getId(), nuevaOrden.getDetalle()); // Registro de información
        return ResponseEntity.ok(nuevaOrden);
    }

    /**
     * Endpoint para actualizar una orden existente.
     *
     * @param idOrden El ID de la orden a actualizar.
     * @param orden La nueva información de la orden.
     * @return ResponseEntity con la orden actualizada y un estado HTTP 200 (OK) o un estado HTTP 404 (Not Found) si la orden no existe.
     * @throws IllegalArgumentException si el ID de la orden es nulo o no positivo.
     */
    @PutMapping("/api/editar/{idOrden}")
    @ResponseBody
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long idOrden, @RequestBody Orden orden) {
        // Validar que el ID de la orden sea positivo
        Preconditions.checkArgument(idOrden != null && idOrden > 0, "El ID de la orden debe ser un valor positivo.");
        
        Orden ordenActualizada = ordenService.actualizarOrden(idOrden, orden);
        if (ordenActualizada != null) {
            logger.info("Orden actualizada: ID={}, Detalle={}", ordenActualizada.getId(), ordenActualizada.getDetalle()); // Registro de información
            return ResponseEntity.ok(ordenActualizada);
        } else {
            logger.warn("Orden con ID: {} no encontrada.", idOrden); // Registro de advertencia
            return ResponseEntity.notFound().build(); // Si no existe la orden, devuelve un 404
        }
    }

    /**
     * Endpoint para eliminar una orden por su ID.
     *
     * @param idOrden El ID de la orden a eliminar.
     * @return ResponseEntity con un estado HTTP 204 (No Content).
     * @throws IllegalArgumentException si el ID de la orden es nulo o no positivo.
     */
    @DeleteMapping("/api/eliminar/{idOrden}")
    @ResponseBody
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long idOrden) {
        // Validar que el ID de la orden sea positivo
        Preconditions.checkArgument(idOrden != null && idOrden > 0, "El ID de la orden debe ser un valor positivo.");
        
        ordenService.eliminarOrden(idOrden);
        logger.info("Orden eliminada: ID={}", idOrden); // Registro de información
        return ResponseEntity.noContent().build();
    }
}
