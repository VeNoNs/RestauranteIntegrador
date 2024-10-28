package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.google.common.base.Joiner;
import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.service.AdministradorEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administradorgeneral")
public class AdministradorEmpresaController {

    @Autowired
    private AdministradorEmpresaService empresaService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verempresas")
    @ResponseBody
    public ResponseEntity<List<AdministradorEmpresa>> listarEmpresas() {
        List<AdministradorEmpresa> empresas = empresaService.obtenerTodos();
        return ResponseEntity.ok(empresas);
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<AdministradorEmpresa> guardarNuevaEmpresa(@RequestBody AdministradorEmpresa empresa) {
        // Validar que la empresa no sea nula
        Preconditions.checkNotNull(empresa, "La empresa no puede ser nula.");
        
        AdministradorEmpresa nuevaEmpresa = empresaService.crearEmpresa(empresa);
        return ResponseEntity.ok(nuevaEmpresa);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<AdministradorEmpresa> actualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody AdministradorEmpresa empresa) {
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");

        AdministradorEmpresa empresaActualizada = empresaService.actualizarEmpresa(idEmpresa, empresa);
        if (empresaActualizada != null) {
            return ResponseEntity.ok(empresaActualizada);
        } else {
            return ResponseEntity.notFound().build();  // Si no existe la empresa, devuelve un 404
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long idEmpresa) {
        Preconditions.checkArgument(idEmpresa != null && idEmpresa > 0, "El ID de la empresa debe ser un valor positivo.");
        
        empresaService.eliminarEmpresa(idEmpresa);
        return ResponseEntity.noContent().build();
    }
}
