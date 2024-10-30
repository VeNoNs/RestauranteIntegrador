package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.AdministradorRestaurante;
import com.restaurante.proyecto.service.AdministradorRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador para manejar las operaciones relacionadas con los administradores del restaurante.
 */
@RestController
@RequestMapping("/administrador-restaurante")
public class AdministradorRestauranteController {

    @Autowired
    private AdministradorRestauranteService adminRestauranteService;

    // 1. GET - Listar todos los administradores de restaurante
    @GetMapping("/api/ver-administradores")
    @ResponseBody
    public List<AdministradorRestaurante> listarAdministradores() {
        return adminRestauranteService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo administrador de restaurante
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<AdministradorRestaurante> crearNuevoAdministrador(@RequestBody AdministradorRestaurante adminRestaurante) {
        AdministradorRestaurante nuevoAdmin = adminRestauranteService.crearAdministradorRestaurante(adminRestaurante);
        return ResponseEntity.ok(nuevoAdmin);
    }

    // 3. PUT - Editar un administrador de restaurante existente
    @PutMapping("/api/editar/{idAdminRestaurante}")
    @ResponseBody
    public ResponseEntity<AdministradorRestaurante> actualizarAdministrador(@PathVariable Long idAdminRestaurante, @RequestBody AdministradorRestaurante adminRestaurante) {
        AdministradorRestaurante adminActualizado = adminRestauranteService.actualizarAdministradorRestaurante(idAdminRestaurante, adminRestaurante);
        if (adminActualizado != null) {
            return ResponseEntity.ok(adminActualizado);
        } else {
            return ResponseEntity.notFound().build();  // Si no existe el administrador, devuelve un 404
        }
    }

    // 4. DELETE - Eliminar un administrador de restaurante
    @DeleteMapping("/api/eliminar/{idAdminRestaurante}")
    @ResponseBody
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long idAdminRestaurante) {
        adminRestauranteService.eliminarAdministradorRestaurante(idAdminRestaurante);
        return ResponseEntity.noContent().build();
    }

    // 5. GET - Contar el número de administradores de restaurante
    @GetMapping("/api/count")
    @ResponseBody
    public long contarAdministradores() {
        return adminRestauranteService.contarAdministradorRestaurante();
    }
}
