package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Comida;
import com.restaurante.proyecto.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/comida")
public class ComidaController {

    private static final Logger LOGGER = Logger.getLogger(ComidaController.class.getName());

    @Autowired
    private ComidaService comidaService;

    @GetMapping("/api/vercomidas")
    @ResponseBody
    public List<Comida> listarComidas() {
        LOGGER.info("Petición recibida para listar todas las comidas.");
        return comidaService.obtenerTodos();
    }

    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<Comida> guardarNuevaComida(@RequestBody Comida comida) {
        LOGGER.info("Petición recibida para guardar una nueva comida: " + comida.getNombreComida());
        Comida nuevaComida = comidaService.crearComida(comida);
        LOGGER.info("Nueva comida creada con éxito: ID " + nuevaComida.getIdComida());
        return ResponseEntity.ok(nuevaComida);
    }

    @PutMapping("/api/editar/{idComida}")
    @ResponseBody
    public ResponseEntity<Comida> actualizarComida(@PathVariable Long idComida, @RequestBody Comida comida) {
        LOGGER.info("Petición recibida para actualizar la comida con ID: " + idComida);
        Comida comidaActualizada = comidaService.actualizarComida(idComida, comida);
        if (comidaActualizada != null) {
            LOGGER.info("Comida actualizada con éxito: ID " + comidaActualizada.getIdComida());
            return ResponseEntity.ok(comidaActualizada);
        } else {
            LOGGER.warning("Comida no encontrada para el ID: " + idComida);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/eliminar/{idComida}")
    @ResponseBody
    public ResponseEntity<Void> eliminarComida(@PathVariable Long idComida) {
        LOGGER.info("Petición recibida para eliminar la comida con ID: " + idComida);
        comidaService.eliminarComida(idComida);
        LOGGER.info("Comida eliminada con éxito: ID " + idComida);
        return ResponseEntity.noContent().build();
    }
}



