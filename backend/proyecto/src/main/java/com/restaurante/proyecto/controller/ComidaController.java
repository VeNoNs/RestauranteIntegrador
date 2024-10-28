package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Comida;
import com.restaurante.proyecto.service.ComidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con las comidas.
 * Proporciona endpoints para listar, crear, actualizar y eliminar comidas.
 */
@Controller
@RequestMapping("/comida")
public class ComidaController {

    private static final Logger logger = LoggerFactory.getLogger(ComidaController.class); // Configuración de Logback

    @Autowired
    private ComidaService comidaService;

    /**
     * Lista todos los registros de comidas.
     *
     * @return Una lista de objetos {@link Comida} que representan todas las comidas en la base de datos.
     */
    @GetMapping("/api/vercomidas")
    @ResponseBody
    public ResponseEntity<List<Comida>> listarComidas() {
        logger.info("Listando todas las comidas."); // Registro de información
        List<Comida> comidas = comidaService.obtenerTodos();
        return ResponseEntity.ok(comidas);
    }

    /**
     * Crea un nuevo registro de comida.
     *
     * @param comida El objeto {@link Comida} que representa la nueva comida a crear.
     * @return El objeto {@link Comida} creado con su ID asignado.
     * @throws NullPointerException si la comida es nula.
     */
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<Comida> guardarNuevaComida(@RequestBody Comida comida) {
        // Validar que la comida no sea nula
        Preconditions.checkNotNull(comida, "La comida no puede ser nula.");
        
        Comida nuevaComida = comidaService.crearComida(comida);
        logger.info("Comida creada: ID={}, Nombre={}", nuevaComida.getId(), nuevaComida.getNombre()); // Registro de información
        return ResponseEntity.ok(nuevaComida);
    }

    /**
     * Actualiza un registro existente de comida.
     *
     * @param idComida El ID de la comida a actualizar.
     * @param comida   El objeto {@link Comida} que contiene los nuevos datos de la comida.
     * @return El objeto {@link Comida} actualizado, o un estado 404 si la comida no fue encontrada.
     * @throws IllegalArgumentException si el ID de la comida no es positivo.
     */
    @PutMapping("/api/editar/{idComida}")
    @ResponseBody
    public ResponseEntity<Comida> actualizarComida(@PathVariable Long idComida, @RequestBody Comida comida) {
        // Validar que el ID de la comida sea positivo
        Preconditions.checkArgument(idComida != null && idComida > 0, "El ID de la comida debe ser un valor positivo.");
        
        Comida comidaActualizada = comidaService.actualizarComida(idComida, comida);
        if (comidaActualizada != null) {
            logger.info("Comida actualizada: ID={}, Nombre={}", comidaActualizada.getId(), comidaActualizada.getNombre()); // Registro de información
            return ResponseEntity.ok(comidaActualizada);
        } else {
            logger.warn("Comida con ID: {} no encontrada.", idComida); // Registro de advertencia
            return ResponseEntity.notFound().build(); // Si no existe la comida, devuelve un 404
        }
    }

    /**
     * Elimina un registro de comida por ID.
     *
     * @param idComida El ID de la comida a eliminar.
     * @return Un estado 204 No Content si la eliminación fue exitosa.
     * @throws IllegalArgumentException si el ID de la comida no es positivo.
     */
    @DeleteMapping("/api/eliminar/{idComida}")
    @ResponseBody
    public ResponseEntity<Void> eliminarComida(@PathVariable Long idComida) {
        // Validar que el ID de la comida sea positivo
        Preconditions.checkArgument(idComida != null && idComida > 0, "El ID de la comida debe ser un valor positivo.");
        
        comidaService.eliminarComida(idComida);
        logger.info("Comida eliminada: ID={}", idComida); // Registro de información
        return ResponseEntity.noContent().build();
    }
}
