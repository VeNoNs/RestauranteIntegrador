package com.restaurante.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurante.proyecto.service.LoginServiceImpl;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String role = loginService.verificarUsuario(loginRequest.getEmail(), loginRequest.getPassword());

        if (role != null) {
            // Si el usuario existe, devolver el rol
            return ResponseEntity.ok(new LoginResponse(role));
        } else {
            // Si no existe, devolver un error de autenticación
            return ResponseEntity.status(401).body("Error de autenticación: Credenciales incorrectas");
        }
    }

    // DTO para manejar las solicitudes y respuestas
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

    static class LoginResponse {
        private String role;

        public LoginResponse(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
