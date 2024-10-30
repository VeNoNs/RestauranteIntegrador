package com.restaurante.proyecto.controller;

import com.restaurante.proyecto.service.Impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Verificar el rol o tipo de empleado con el servicio de login
        String roleOrTipoEmpleado = loginService.verificarUsuario(loginRequest.getEmail(), loginRequest.getPassword());

        if (roleOrTipoEmpleado != null) {
            // Si el usuario existe, devolver el rol o el tipo de empleado
            return ResponseEntity.ok(roleOrTipoEmpleado);
        } else {
            // Si no existe, devolver un error de autenticación
            return ResponseEntity.status(401).body("Error de autenticación: Credenciales incorrectas");
        }
    }

    // DTO para manejar las solicitudes
    static class LoginRequest {
        private String email;
        private String password;

        // Getters y Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
