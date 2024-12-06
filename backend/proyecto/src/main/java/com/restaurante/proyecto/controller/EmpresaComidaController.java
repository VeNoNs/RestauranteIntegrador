package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.EmpresaComida;
import com.restaurante.proyecto.service.EmpresaComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con las empresas.
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaComidaController {

    @Autowired
    private EmpresaComidaService empresaComidaService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verempresas")
    @ResponseBody
    public List<EmpresaComida> listarEmpresas() {
        return empresaComidaService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nueva")
    @ResponseBody
    public ResponseEntity<EmpresaComida> guardarNuevaEmpresa(@RequestBody EmpresaComida empresa) {
        EmpresaComida nuevaEmpresa = empresaComidaService.crearEmpresa(empresa);
        return ResponseEntity.ok(nuevaEmpresa);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<EmpresaComida> actualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody EmpresaComida empresa) {
        EmpresaComida empresaActualizada = empresaComidaService.actualizarEmpresa(idEmpresa, empresa);
        if (empresaActualizada != null) {
            return ResponseEntity.ok(empresaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idEmpresa}")
    @ResponseBody
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long idEmpresa) {
        empresaComidaService.eliminarEmpresa(idEmpresa);
        return ResponseEntity.noContent().build();
    }
}
