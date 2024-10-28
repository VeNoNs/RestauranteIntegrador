package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
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
    public ResponseEntity<List<Local>> listarLocales() {
        List<Local> locales = localService.obtenerTodos();
        return ResponseEntity.ok(locales);
    }

    // 2. POST - Crear un nuevo registro en la tabla
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Local> guardarNuevoLocal(@RequestBody Local local) {
        // Validar que el local no sea nulo
        Preconditions.checkNotNull(local, "El local no puede ser nulo.");

        Local nuevoLocal = localService.crearLocal(local);
        return ResponseEntity.ok(nuevoLocal);
    }

    // 3. PUT - Editar un registro existente
    @PutMapping("/api/editar/{idLocal}")
    @ResponseBody
    public ResponseEntity<Local> actualizarLocal(@PathVariable Long idLocal, @RequestBody Local local) {
        // Validar que el ID del local sea positivo
        Preconditions.checkArgument(idLocal != null && idLocal > 0, "El ID del local debe ser un valor positivo.");

        Local localActualizado = localService.actualizarLocal(idLocal, local);
        if (localActualizado != null) {
            return ResponseEntity.ok(localActualizado);
        } else {
            return ResponseEntity.notFound().build(); // Si no existe el local, devuelve un 404
        }
    }

    // 4. DELETE - Eliminar un registro por ID
    @DeleteMapping("/api/eliminar/{idLocal}")
    @ResponseBody
    public ResponseEntity<Void> eliminarLocal(@PathVariable Long idLocal) {
        // Validar que el ID del local sea positivo
        Preconditions.checkArgument(idLocal != null && idLocal > 0, "El ID del local debe ser un valor positivo.");

        localService.eliminarLocal(idLocal);
        return ResponseEntity.noContent().build();
    }
}
