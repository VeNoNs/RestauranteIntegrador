package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Pago;
import com.restaurante.proyecto.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con los pagos.
 */
@Controller
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verpagos")
    @ResponseBody
    public List<Pago> listarPagos() {
        return pagoService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Pago> guardarNuevoPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.crearPago(pago);
        return ResponseEntity.ok(nuevoPago);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idPago}")
    @ResponseBody
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long idPago, @RequestBody Pago pago) {
        Pago pagoActualizado = pagoService.actualizarPago(idPago, pago);
        if (pagoActualizado != null) {
            return ResponseEntity.ok(pagoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idPago}")
    @ResponseBody
    public ResponseEntity<Void> eliminarPago(@PathVariable Long idPago) {
        pagoService.eliminarPago(idPago);
        return ResponseEntity.noContent().build();
    }
}
