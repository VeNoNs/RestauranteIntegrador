package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.service.EmpleadoService;
import com.restaurante.proyecto.service.Impl.ExcelService; // Importar el servicio de Excel
import org.apache.commons.validator.routines.EmailValidator; // Importar el validador de correo electrónico
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con empleados.
 */
@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class); // Configuración de Logback

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ExcelService excelService; // Inyección del servicio de Excel

    /**
     * Lista todos los empleados.
     *
     * @return una respuesta con la lista de empleados y un estado HTTP 200 OK.
     */
    @GetMapping("/api/verempleados")
    @ResponseBody
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        logger.info("Listando todos los empleados."); // Registro de información
        List<Empleado> empleados = empleadoService.obtenerTodos();
        return ResponseEntity.ok(empleados);
    }

    /**
     * Crea un nuevo empleado.
     *
     * @param empleado el empleado a crear.
     * @return una respuesta con el empleado creado y un estado HTTP 200 OK.
     */
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Empleado> guardarNuevoEmpleado(@RequestBody Empleado empleado) {
        // Validar que el empleado no sea nulo
        Preconditions.checkNotNull(empleado, "El empleado no puede ser nulo.");
        
        // Validación del correo electrónico
        if (!EmailValidator.getInstance().isValid(empleado.getEmail())) {
            logger.warn("El correo electrónico proporcionado no es válido: {}", empleado.getEmail()); // Registro de advertencia
            return ResponseEntity.badRequest().body(null); 
        }

        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        logger.info("Empleado creado: ID={}, Nombre={}", nuevoEmpleado.getId(), nuevoEmpleado.getNombre()); // Registro de información
        return ResponseEntity.ok(nuevoEmpleado);
    }

    /**
     * Actualiza un empleado existente.
     *
     * @param idEmpleado el ID del empleado a actualizar.
     * @param empleado   el empleado con los nuevos datos.
     * @return una respuesta con el empleado actualizado o un estado HTTP 404 si no se encuentra.
     */
    @PutMapping("/api/editar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        // Validar que el ID del empleado sea positivo
        Preconditions.checkArgument(idEmpleado != null && idEmpleado > 0, "El ID del empleado debe ser un valor positivo.");
        
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(idEmpleado, empleado);
        if (empleadoActualizado != null) {
            logger.info("Empleado actualizado: ID={}, Nombre={}", empleadoActualizado.getId(), empleadoActualizado.getNombre()); // Registro de información
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            logger.warn("Empleado con ID: {} no encontrado.", idEmpleado); // Registro de advertencia
            return ResponseEntity.notFound().build(); // Si no existe el empleado, devuelve un 404
        }
    }

    /**
     * Elimina un empleado por su ID.
     *
     * @param idEmpleado el ID del empleado a eliminar.
     * @return una respuesta con estado HTTP 204 No Content si se elimina exitosamente.
     */
    @DeleteMapping("/api/eliminar/{idEmpleado}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idEmpleado) {
        // Validar que el ID del empleado sea positivo
        Preconditions.checkArgument(idEmpleado != null && idEmpleado > 0, "El ID del empleado debe ser un valor positivo.");
        
        empleadoService.eliminarEmpleado(idEmpleado);
        logger.info("Empleado eliminado: ID={}", idEmpleado); // Registro de información
        return ResponseEntity.noContent().build();
    }

    /**
     * Descarga un informe de empleados en formato Excel.
     *
     * @return una respuesta con el archivo Excel generado y un estado HTTP 200 OK.
     */
    @GetMapping("/api/informe-empleados")
    public ResponseEntity<byte[]> descargarInformeEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodos();
        byte[] excelFile;

        try {
            logger.info("Generando informe de empleados."); // Registro de información
            excelFile = excelService.generarInformeEmpleados(empleados);
            logger.info("Informe generado exitosamente, número de empleados: {}", empleados.size()); // Registro de información
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=empleados.xlsx")
                    .body(excelFile);
        } catch (IOException e) {
            logger.error("Error al generar el informe de empleados: {}", e.getMessage(), e); // Registro de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
