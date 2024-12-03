package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // Configuración del logger
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verempleados")
    @ResponseBody
    public List<Empleado> listarEmpleados() {
        logger.info("Solicitud recibida: Listar todos los empleados.");
        List<Empleado> empleados = empleadoService.obtenerTodos();
        logger.info("Se encontraron {} empleados en la base de datos.", empleados.size());
        return empleados;
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Empleado> guardarNuevoEmpleado(@RequestBody Empleado empleado) {
        logger.info("Solicitud recibida: Crear un nuevo empleado con nombre '{}'.", empleado.getNombreEmpleado());
        try {
            Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
            logger.info("Empleado creado exitosamente con ID: {}.", nuevoEmpleado.getIdEmpleado());
            return ResponseEntity.ok(nuevoEmpleado);
        } catch (Exception e) {
            logger.error("Error al crear el empleado: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        logger.info("Solicitud recibida: Actualizar empleado con ID: {}.", idEmpleado);
        try {
            Empleado empleadoActualizado = empleadoService.actualizarEmpleado(idEmpleado, empleado);
            if (empleadoActualizado != null) {
                logger.info("Empleado actualizado exitosamente: {}.", empleadoActualizado.getNombreEmpleado());
                return ResponseEntity.ok(empleadoActualizado);
            } else {
                logger.warn("No se encontró el empleado con ID: {}.", idEmpleado);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al actualizar el empleado con ID {}: {}", idEmpleado, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        logger.info("Solicitud recibida: Eliminar empleado con ID: {}.", idEmpleado);
        try {
            empleadoService.eliminarEmpleado(idEmpleado);
            logger.info("Empleado eliminado exitosamente con ID: {}.", idEmpleado);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar el empleado con ID {}: {}", idEmpleado, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}

