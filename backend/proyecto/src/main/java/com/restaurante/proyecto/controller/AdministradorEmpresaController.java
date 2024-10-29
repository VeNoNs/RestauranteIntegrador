package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.service.AdministradorEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador-general")
public class AdministradorEmpresaController {

    @Autowired
    private AdministradorEmpresaService empresaService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/ver-administradores")
    @ResponseBody
    public List<AdministradorEmpresa> listarEmpresas() {
        return empresaService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<AdministradorEmpresa> guardarNuevaEmpresa(@RequestBody AdministradorEmpresa empresa) {
        AdministradorEmpresa nuevaEmpresa = empresaService.crearAdministradorGeneral(empresa);
        return ResponseEntity.ok(nuevaEmpresa);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idAdminGeneral}")
    @ResponseBody
    public ResponseEntity<AdministradorEmpresa> actualizarEmpresa(@PathVariable Long idAdminGeneral, @RequestBody AdministradorEmpresa empresa) {
        AdministradorEmpresa empresaActualizada = empresaService.actualizarAdministradorGeneral(idAdminGeneral, empresa);
        if (empresaActualizada != null) {
            return ResponseEntity.ok(empresaActualizada);
        } else {
            return ResponseEntity.notFound().build();  // Si no existe la empresa, devuelve un 404
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idAdminGeneral}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long idAdminGeneral) {
        empresaService.eliminarAdministradorGeneral(idAdminGeneral);
        return ResponseEntity.noContent().build();
    }
}
