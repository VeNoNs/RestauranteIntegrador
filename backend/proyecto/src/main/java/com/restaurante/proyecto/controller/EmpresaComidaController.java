package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.EmpresaComida;
import com.restaurante.proyecto.service.EmpresaComidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con la entidad EmpresaComida.
 * Proporciona endpoints para listar, crear, actualizar y eliminar empresas de comida.
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaComidaController {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaComidaController.class); // Configuración de Logback

    @Autowired
    private EmpresaComidaService empresaComidaService;

    /**
     * Endpoint para listar todas las empresas de comida.
     *
     * @return ResponseEntity con la lista de empresas de comida y un estado HTTP 200 (OK).
     */
    @GetMapping("/api/verempresas")
    @ResponseBody
    public ResponseEntity<List<EmpresaComida>> listarEmpresas() {
        logger.info("Listando todas las empresas de comida."); // Registro de información
        List<EmpresaComida> empresas = empresaComidaService.obtenerTodos();
        return ResponseEntity.ok(empresas);
    }

    /**
     * Endpoint para crear una nueva empresa de comida.
     *
     * @param empresa La empresa de comida a crear.
     * @return ResponseEntity con la empresa creada y un estado HTTP 200 (OK).
     * @throws NullPointerException si la empresa proporcionada es nula.
     */
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<EmpresaComida> guardarNuevaEmpresa(@RequestBody EmpresaComida empresa) {
        // Validar que la empresa no sea nula
        Preconditions.checkNotNull(empresa, "La empresa no puede ser nula.");
        
        EmpresaComida nuevaEmpresa = empresaComidaService.crearEmpresa(empresa);
        logger.info("Empresa creada: ID={}, Nombre={}", nuevaEmpresa.getId(), nuevaEmpresa.getNombre()); // Registro de información
        return ResponseEntity.ok(nuevaEmpresa);
    }

    /**
     * Endpoint para actualizar una empresa existente.
     *
     * @param idEmpresa El ID de la empresa a actualizar.
     * @param empresa La nueva información de la empresa.
     * @return ResponseEntity con la empresa actualizada y un estado HTTP 200 (OK) o un estado HTTP 404 (Not Found) si la empresa no existe.
     * @throws IllegalArgumentException si el ID de la empresa es nulo o no positivo.
     */
    @PutMapping("/api/editar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<EmpresaComida> actualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody EmpresaComida empresa) {
        // Validar que el ID de la empresa sea positivo
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");
        
        EmpresaComida empresaActualizada = empresaComidaService.actualizarEmpresa(idEmpresa, empresa);
        if (empresaActualizada != null) {
            logger.info("Empresa actualizada: ID={}, Nombre={}", empresaActualizada.getId(), empresaActualizada.getNombre()); // Registro de información
            return ResponseEntity.ok(empresaActualizada);
        } else {
            logger.warn("Empresa con ID: {} no encontrada.", idEmpresa); // Registro de advertencia
            return ResponseEntity.notFound().build(); // Si no existe la empresa, devuelve un 404
        }
    }

    /**
     * Endpoint para eliminar una empresa por su ID.
     *
     * @param idEmpresa El ID de la empresa a eliminar.
     * @return ResponseEntity con un estado HTTP 204 (No Content).
     * @throws IllegalArgumentException si el ID de la empresa es nulo o no positivo.
     */
    @DeleteMapping("/api/eliminar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long idEmpresa) {
        // Validar que el ID de la empresa sea positivo
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");
        
        empresaComidaService.eliminarEmpresa(idEmpresa);
        logger.info("Empresa eliminada: ID={}", idEmpresa); // Registro de información
        return ResponseEntity.noContent().build();
    }
}
