package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Comensal;
import com.restaurante.proyecto.service.ComensalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con los comensales.
 * Proporciona endpoints para listar, crear, actualizar y eliminar comensales.
 */
@Controller
@RequestMapping("/comensal")
public class ComensalController {

    private static final Logger logger = LoggerFactory.getLogger(ComensalController.class); // Configuración de Logback

    @Autowired
    private ComensalService comensalService;

    /**
     * Lista todos los registros de comensales.
     *
     * @return Una lista de objetos {@link Comensal} que representan todos los comensales en la base de datos.
     */
    @GetMapping("/api/vercomensales")
    @ResponseBody
    public ResponseEntity<List<Comensal>> listarComensales() {
        logger.info("Listando todos los comensales."); // Registro de información
        List<Comensal> comensales = comensalService.obtenerTodos();
        return ResponseEntity.ok(comensales);
    }

    /**
     * Crea un nuevo registro de comensal.
     *
     * @param comensal El objeto {@link Comensal} que representa el nuevo comensal a crear.
     * @return El objeto {@link Comensal} creado con su ID asignado.
     * @throws NullPointerException si el comensal es nulo.
     */
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Comensal> guardarNuevoComensal(@RequestBody Comensal comensal) {
        // Validar que el comensal no sea nulo
        Preconditions.checkNotNull(comensal, "El comensal no puede ser nulo.");
        
        Comensal nuevoComensal = comensalService.crearComensal(comensal);
        logger.info("Comensal creado: ID={}, Nombre={}", nuevoComensal.getId(), nuevoComensal.getNombre()); // Registro de información
        return ResponseEntity.ok(nuevoComensal);
    }

    /**
     * Actualiza un registro existente de comensal.
     *
     * @param idComensal El ID del comensal a actualizar.
     * @param comensal   El objeto {@link Comensal} que contiene los nuevos datos del comensal.
     * @return El objeto {@link Comensal} actualizado, o un estado 404 si el comensal no fue encontrado.
     * @throws IllegalArgumentException si el ID del comensal no es positivo.
     */
    @PutMapping("/api/editar/{idComensal}")
    @ResponseBody
    public ResponseEntity<Comensal> actualizarComensal(@PathVariable Long idComensal, @RequestBody Comensal comensal) {
        // Validar que el ID del comensal sea positivo
        Preconditions.checkArgument(idComensal != null && idComensal > 0, "El ID del comensal debe ser un valor positivo.");

        Comensal comensalActualizado = comensalService.actualizarComensal(idComensal, comensal);
        if (comensalActualizado != null) {
            logger.info("Comensal actualizado: ID={}, Nombre={}", comensalActualizado.getId(), comensalActualizado.getNombre()); // Registro de información
            return ResponseEntity.ok(comensalActualizado);
        } else {
            logger.warn("Comensal con ID: {} no encontrado.", idComensal); // Registro de advertencia
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un registro de comensal por ID.
     *
     * @param idComensal El ID del comensal a eliminar.
     * @return Un estado 204 No Content si la eliminación fue exitosa.
     * @throws IllegalArgumentException si el ID del comensal no es positivo.
     */
    @DeleteMapping("/api/eliminar/{idComensal}")
    @ResponseBody
    public ResponseEntity<Void> eliminarComensal(@PathVariable Long idComensal) {
        // Validar que el ID del comensal sea positivo
        Preconditions.checkArgument(idComensal != null && idComensal > 0, "El ID del comensal debe ser un valor positivo.");
        
        comensalService.eliminarComensal(idComensal);
        logger.info("Comensal eliminado: ID={}", idComensal); // Registro de información
        return ResponseEntity.noContent().build();
    }
}
