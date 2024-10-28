package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.service.EmpleadoService;
import com.restaurante.proyecto.service.Impl.ExcelService; // Importar el servicio de Excel
import org.apache.commons.validator.routines.EmailValidator; // Importar el validador de correo electrónico
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ExcelService excelService; // Inyección del servicio de Excel

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
        
        // Validación del correo electrónico
        if (!EmailValidator.getInstance().isValid(empleado.getEmail())) {
            return ResponseEntity.badRequest().body(null); 
        }

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

    // 5. GET - Descargar informe de empleados en formato Excel
    @GetMapping("/api/informe-empleados")
    public ResponseEntity<byte[]> descargarInformeEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodos();
        byte[] excelFile;

        try {
            excelFile = excelService.generarInformeEmpleados(empleados);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=empleados.xlsx")
                    .body(excelFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
