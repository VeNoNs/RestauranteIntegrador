package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.EmpresaComida;
import com.restaurante.proyecto.service.EmpresaComidaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaComidaController {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaComidaController.class);

    @Autowired
    private EmpresaComidaService empresaComidaService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verempresas")
    @ResponseBody
    public List<EmpresaComida> listarEmpresas() {
        logger.info("Solicitud recibida: Listar todas las empresas de comida.");
        List<EmpresaComida> empresas = empresaComidaService.obtenerTodos();
        logger.info("Empresas encontradas: {} registros.", empresas.size());
        return empresas;
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<EmpresaComida> guardarNuevaEmpresa(@RequestBody EmpresaComida empresa) {
        logger.info("Solicitud recibida: Crear una nueva empresa de comida con nombre '{}'.", empresa.getIdEmpresa());
        try {
            EmpresaComida nuevaEmpresa = empresaComidaService.crearEmpresa(empresa);
            logger.info("Nueva empresa creada exitosamente con ID {}.", nuevaEmpresa.getIdEmpresa());
            return ResponseEntity.ok(nuevaEmpresa);
        } catch (Exception e) {
            logger.error("Error al crear la nueva empresa: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<EmpresaComida> actualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody EmpresaComida empresa) {
        logger.info("Solicitud recibida: Actualizar empresa con ID {}.", idEmpresa);
        try {
            EmpresaComida empresaActualizada = empresaComidaService.actualizarEmpresa(idEmpresa, empresa);
            if (empresaActualizada != null) {
                logger.info("Empresa actualizada exitosamente con ID {}.", idEmpresa);
                return ResponseEntity.ok(empresaActualizada);
            } else {
                logger.warn("No se encontró una empresa con ID {} para actualizar.", idEmpresa);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al actualizar la empresa con ID {}: {}", idEmpresa, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long idEmpresa) {
        logger.info("Solicitud recibida: Eliminar empresa con ID {}.", idEmpresa);
        try {
            empresaComidaService.eliminarEmpresa(idEmpresa);
            logger.info("Empresa eliminada exitosamente con ID {}.", idEmpresa);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar la empresa con ID {}: {}", idEmpresa, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}

