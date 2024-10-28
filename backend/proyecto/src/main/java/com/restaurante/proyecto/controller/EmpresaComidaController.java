package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.EmpresaComida;
import com.restaurante.proyecto.service.EmpresaComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaComidaController {

    @Autowired
    private EmpresaComidaService empresaComidaService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verempresas")
    @ResponseBody
    public ResponseEntity<List<EmpresaComida>> listarEmpresas() {
        List<EmpresaComida> empresas = empresaComidaService.obtenerTodos();
        return ResponseEntity.ok(empresas);
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<EmpresaComida> guardarNuevaEmpresa(@RequestBody EmpresaComida empresa) {
        // Validar que la empresa no sea nula
        Preconditions.checkNotNull(empresa, "La empresa no puede ser nula.");

        EmpresaComida nuevaEmpresa = empresaComidaService.crearEmpresa(empresa);
        return ResponseEntity.ok(nuevaEmpresa);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<EmpresaComida> actualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody EmpresaComida empresa) {
        // Validar que el ID de la empresa sea positivo
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");

        EmpresaComida empresaActualizada = empresaComidaService.actualizarEmpresa(idEmpresa, empresa);
        if (empresaActualizada != null) {
            return ResponseEntity.ok(empresaActualizada);
        } else {
            return ResponseEntity.notFound().build(); // Si no existe la empresa, devuelve un 404
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long idEmpresa) {
        // Validar que el ID de la empresa sea positivo
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");

        empresaComidaService.eliminarEmpresa(idEmpresa);
        return ResponseEntity.noContent().build();
    }
}
