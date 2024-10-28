package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.service.AdministradorEmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con las empresas administradoras.
 * Proporciona endpoints para listar, crear, actualizar y eliminar empresas.
 */
@Controller
@RequestMapping("/administradorgeneral")
public class AdministradorEmpresaController {

    private static final Logger logger = LoggerFactory.getLogger(AdministradorEmpresaController.class); // Configuración de Logback

    @Autowired
    private AdministradorEmpresaService empresaService;

    /**
     * Lista todos los registros de empresas.
     *
     * @return Una lista de objetos {@link AdministradorEmpresa} que representan todas las empresas en la base de datos.
     */
    @GetMapping("/api/verempresas")
    @ResponseBody
    public ResponseEntity<List<AdministradorEmpresa>> listarEmpresas() {
        logger.info("Listando todas las empresas."); // Registro de información
        List<AdministradorEmpresa> empresas = empresaService.obtenerTodos();
        return ResponseEntity.ok(empresas);
    }

    /**
     * Crea un nuevo registro de empresa.
     *
     * @param empresa El objeto {@link AdministradorEmpresa} que representa la nueva empresa a crear.
     * @return El objeto {@link AdministradorEmpresa} creado con su ID asignado.
     * @throws NullPointerException si la empresa es nula.
     */
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<AdministradorEmpresa> guardarNuevaEmpresa(@RequestBody AdministradorEmpresa empresa) {
        // Validar que la empresa no sea nula
        Preconditions.checkNotNull(empresa, "La empresa no puede ser nula.");
        
        AdministradorEmpresa nuevaEmpresa = empresaService.crearEmpresa(empresa);
        logger.info("Empresa creada: ID={}, Nombre={}", nuevaEmpresa.getId(), nuevaEmpresa.getNombre()); // Registro de información
        return ResponseEntity.ok(nuevaEmpresa);
    }

    /**
     * Actualiza un registro existente de empresa.
     *
     * @param idEmpresa El ID de la empresa a actualizar.
     * @param empresa   El objeto {@link AdministradorEmpresa} que contiene los nuevos datos de la empresa.
     * @return El objeto {@link AdministradorEmpresa} actualizado, o un estado 404 si la empresa no fue encontrada.
     * @throws IllegalArgumentException si el ID de la empresa no es positivo.
     */
    @PutMapping("/api/editar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<AdministradorEmpresa> actualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody AdministradorEmpresa empresa) {
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");

        AdministradorEmpresa empresaActualizada = empresaService.actualizarEmpresa(idEmpresa, empresa);
        if (empresaActualizada != null) {
            logger.info("Empresa actualizada: ID={}, Nombre={}", empresaActualizada.getId(), empresaActualizada.getNombre()); // Registro de información
            return ResponseEntity.ok(empresaActualizada);
        } else {
            logger.warn("Empresa con ID: {} no encontrada.", idEmpresa); // Registro de advertencia
            return ResponseEntity.notFound().build();  // Si no existe la empresa, devuelve un 404
        }
    }

    /**
     * Elimina un registro de empresa por ID.
     *
     * @param idEmpresa El ID de la empresa a eliminar.
     * @return Un estado 204 No Content si la eliminación fue exitosa.
     * @throws IllegalArgumentException si el ID de la empresa no es positivo.
     */
    @DeleteMapping("/api/eliminar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long idEmpresa) {
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");
        
        empresaService.eliminarEmpresa(idEmpresa);
        logger.info("Empresa eliminada: ID={}", idEmpresa); // Registro de información
        return ResponseEntity.noContent().build();
    }
}
