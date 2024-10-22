package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.entities.Local;
import com.restaurante.proyecto.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/local")
public class LocalController {

    @Autowired
    private LocalService localService;

    // 1. GET - Listar todos los registros de la tabla
    @GetMapping("/api/verlocales")
    @ResponseBody
    public List<Local> listarLocales() {
        return localService.obtenerTodos();
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Local> guardarNuevoLocal(@RequestBody Local local) {
        Local nuevoLocal = localService.crearLocal(local);
        return ResponseEntity.ok(nuevoLocal);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idLocal}")
    @ResponseBody
    public ResponseEntity<Local> actualizarLocal(@PathVariable Long idLocal, @RequestBody Local local) {
        Local localActualizado = localService.actualizarLocal(idLocal, local);
        if (localActualizado != null) {
            return ResponseEntity.ok(localActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idLocal}")
    @ResponseBody
    public ResponseEntity<Void> eliminarLocal(@PathVariable Long idLocal) {
        localService.eliminarLocal(idLocal);
        return ResponseEntity.noContent().build();
    }
}
