package com.restaurante.proyecto.controller;

import com.google.common.base.Preconditions;
import com.restaurante.proyecto.entities.Local;
import com.restaurante.proyecto.service.LocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importar Logback
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con la entidad Local.
 * Proporciona endpoints para listar, crear, actualizar y eliminar locales.
 */
@Controller
@RequestMapping("/local")
public class LocalController {

    private static final Logger logger = LoggerFactory.getLogger(LocalController.class); // Configuración de Logback

    @Autowired
    private LocalService localService;

    /**
     * Endpoint para listar todos los locales.
     *
     * @return ResponseEntity con la lista de locales y un estado HTTP 200 (OK).
     */
    @GetMapping("/api/verlocales")
    @ResponseBody
    public ResponseEntity<List<Local>> listarLocales() {
        logger.info("Listando todos los locales."); // Registro de información
        List<Local> locales = localService.obtenerTodos();
        return ResponseEntity.ok(locales);
    }

    /**
     * Endpoint para crear un nuevo local.
     *
     * @param local El local a crear.
     * @return ResponseEntity con el local creado y un estado HTTP 200 (OK).
     * @throws NullPointerException si el local proporcionado es nulo.
     */
    @PostMapping("/api/nuevo")
    @ResponseBody
    public ResponseEntity<Local> guardarNuevoLocal(@RequestBody Local local) {
        // Validar que el local no sea nulo
        Preconditions.checkNotNull(local, "El local no puede ser nulo.");
        
        Local nuevoLocal = localService.crearLocal(local);
        logger.info("Local creado: ID={}, Nombre={}", nuevoLocal.getId(), nuevoLocal.getNombre()); // Registro de información
        return ResponseEntity.ok(nuevoLocal);
    }

    /**
     * Endpoint para actualizar un local existente.
     *
     * @param idLocal El ID del local a actualizar.
     * @param local La nueva información del local.
     * @return ResponseEntity con el local actualizado y un estado HTTP 200 (OK) o un estado HTTP 404 (Not Found) si el local no existe.
     * @throws IllegalArgumentException si el ID del local es nulo o no positivo.
     */
    @PutMapping("/api/editar/{idLocal}")
    @ResponseBody
    public ResponseEntity<Local> actualizarLocal(@PathVariable Long idLocal, @RequestBody Local local) {
        // Validar que el ID del local sea positivo
        Preconditions.checkArgument(idLocal != null && idLocal > 0, "El ID del local debe ser un valor positivo.");
        
        Local localActualizado = localService.actualizarLocal(idLocal, local);
        if (localActualizado != null) {
            logger.info("Local actualizado: ID={}, Nombre={}", localActualizado.getId(), localActualizado.getNombre()); // Registro de información
            return ResponseEntity.ok(localActualizado);
        } else {
            logger.warn("Local con ID: {} no encontrado.", idLocal); // Registro de advertencia
            return ResponseEntity.notFound().build(); // Si no existe el local, devuelve un 404
        }
    }

    /**
     * Endpoint para eliminar un local por su ID.
     *
     * @param idLocal El ID del local a eliminar.
     * @return ResponseEntity con un estado HTTP 204 (No Content).
     * @throws IllegalArgumentException si el ID del local es nulo o no positivo.
     */
    @DeleteMapping("/api/eliminar/{idLocal}")
    @ResponseBody
    public ResponseEntity<Void> eliminarLocal(@PathVariable Long idLocal) {
        // Validar que el ID del local sea positivo
        Preconditions.checkArgument(idLocal != null && idLocal > 0, "El ID del local debe ser un valor positivo.");
        
        localService.eliminarLocal(idLocal);
        logger.info("Local eliminado: ID={}", idLocal); // Registro de información
        return ResponseEntity.noContent().build();
    }
}
