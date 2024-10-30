package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Reserva;
import com.restaurante.proyecto.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con las reservas.
 */
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // 1. GET - Listar todas las reservas
    @GetMapping("/listar")
    public List<Reserva> listarReservas() {
        return reservaService.obtenerTodos();
    }

    // 2. GET - Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReserva(@PathVariable Long id) {
        Reserva reserva = reservaService.obtenerPorId(id);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. POST - Crear una nueva reserva
    @PostMapping("/crear")
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        return ResponseEntity.ok(nuevaReserva);
    }

    // 4. PUT - Actualizar una reserva existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        Reserva reservaActualizada = reservaService.actualizarReserva(id, reserva);
        if (reservaActualizada != null) {
            return ResponseEntity.ok(reservaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE - Eliminar una reserva
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
