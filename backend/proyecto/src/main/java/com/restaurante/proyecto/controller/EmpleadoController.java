package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con los empleados.
 */
@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verempleados")
    @ResponseBody
    public List<Empleado> listarEmpleados() {
        return empleadoService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Empleado> guardarNuevoEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(idEmpleado, empleado);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}
