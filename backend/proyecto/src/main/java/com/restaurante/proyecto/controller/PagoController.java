package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Pago;
import com.restaurante.proyecto.service.PagoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con la entidad Pago.
 * Proporciona endpoints para listar, crear, actualizar y eliminar pagos.
 */
@Controller
@RequestMapping("/pago")
public class PagoController {

    private static final Logger logger = LoggerFactory.getLogger(PagoController.class); // Configuración de Logback

    @Autowired
    private PagoService pagoService;

    /**
     * Endpoint para listar todos los pagos.
     *
     * @return ResponseEntity con la lista de pagos y un estado HTTP 200 (OK).
     */
    @GetMapping("/api/verpagos")
    @ResponseBody
    public ResponseEntity<List<Pago>> listarPagos() {
        logger.info("Listando todos los pagos."); // Registro de información
        List<Pago> pagos = pagoService.obtenerTodos();
        return ResponseEntity.ok(pagos);
    }

    /**
     * Endpoint para crear un nuevo pago.
     *
     * @param pago El pago a crear.
     * @return ResponseEntity con el pago creado y un estado HTTP 200 (OK).
     * @throws NullPointerException si el pago proporcionado es nulo.
     */
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Pago> guardarNuevoPago(@RequestBody Pago pago) {
        // Validar que el pago no sea nulo
        Preconditions.checkNotNull(pago, "El pago no puede ser nulo.");
        
        Pago nuevoPago = pagoService.crearPago(pago);
        logger.info("Pago creado: ID={}, Monto={}", nuevoPago.getId(), nuevoPago.getMonto()); // Registro de información
        return ResponseEntity.ok(nuevoPago);
    }

    /**
     * Endpoint para actualizar un pago existente.
     *
     * @param idPago El ID del pago a actualizar.
     * @param pago La nueva información del pago.
     * @return ResponseEntity con el pago actualizado y un estado HTTP 200 (OK) o un estado HTTP 404 (Not Found) si el pago no existe.
     * @throws IllegalArgumentException si el ID del pago es nulo o no positivo.
     */
    @PutMapping("/api/editar/{idPago}")
    @ResponseBody
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long idPago, @RequestBody Pago pago) {
        // Validar que el ID del pago sea positivo
        Preconditions.checkArgument(idPago != null && idPago > 0, "El ID del pago debe ser un valor positivo.");
        
        Pago pagoActualizado = pagoService.actualizarPago(idPago, pago);
        if (pagoActualizado != null) {
            logger.info("Pago actualizado: ID={}, Monto={}", pagoActualizado.getId(), pagoActualizado.getMonto()); // Registro de información
            return ResponseEntity.ok(pagoActualizado);
        } else {
            logger.warn("Pago con ID: {} no encontrado.", idPago); // Registro de advertencia
            return ResponseEntity.notFound().build(); // Si no existe el pago, devuelve un 404
        }
    }

    /**
     * Endpoint para eliminar un pago por su ID.
     *
     * @param idPago El ID del pago a eliminar.
     * @return ResponseEntity con un estado HTTP 204 (No Content).
     * @throws IllegalArgumentException si el ID del pago es nulo o no positivo.
     */
    @DeleteMapping("/api/eliminar/{idPago}")
    @ResponseBody
    public ResponseEntity<Void> eliminarPago(@PathVariable Long idPago) {
        // Validar que el ID del pago sea positivo
        Preconditions.checkArgument(idPago != null && idPago > 0, "El ID del pago debe ser un valor positivo.");
        
        pagoService.eliminarPago(idPago);
        logger.info("Pago eliminado: ID={}", idPago); // Registro de información
        return ResponseEntity.noContent().build();
    }
}
