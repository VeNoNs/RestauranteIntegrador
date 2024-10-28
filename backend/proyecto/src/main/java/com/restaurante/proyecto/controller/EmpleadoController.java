package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verempleados")
    @ResponseBody
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodos();
        return ResponseEntity.ok(empleados);
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Empleado> guardarNuevoEmpleado(@RequestBody Empleado empleado) {
        // Validar que el empleado no sea nulo
        Preconditions.checkNotNull(empleado, "El empleado no puede ser nulo.");
        
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        // Validar que el ID del empleado sea positivo
        Preconditions.checkArgument(idEmpleado != null && idEmpleado > 0, "El ID del empleado debe ser un valor positivo.");
        
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(idEmpleado, empleado);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build(); // Si no existe el empleado, devuelve un 404
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        // Validar que el ID del empleado sea positivo
        Preconditions.checkArgument(idEmpleado != null && idEmpleado > 0, "El ID del empleado debe ser un valor positivo.");
        
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}
