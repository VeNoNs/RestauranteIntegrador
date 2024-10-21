package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Comensal;
import com.restaurante.proyecto.service.ComensalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comensal")
public class ComensalController {

    @Autowired
    private ComensalService comensalService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/vercomensales")
    @ResponseBody
    public List<Comensal> listarComensales() {
        return comensalService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Comensal> guardarNuevoComensal(@RequestBody Comensal comensal) {
        Comensal nuevoComensal = comensalService.crearComensal(comensal);
        return ResponseEntity.ok(nuevoComensal);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idComensal}")
    @ResponseBody
    public ResponseEntity<Comensal> actualizarComensal(@PathVariable Long idComensal, @RequestBody Comensal comensal) {
        Comensal comensalActualizado = comensalService.actualizarComensal(idComensal, comensal);
        if (comensalActualizado != null) {
            return ResponseEntity.ok(comensalActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idComensal}")
    @ResponseBody
    public ResponseEntity<Void> eliminarComensal(@PathVariable Long idComensal) {
        comensalService.eliminarComensal(idComensal);
        return ResponseEntity.noContent().build();
    }
}
