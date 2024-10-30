package com.restaurante.proyecto.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.proyecto.entities.AdministradorEmpresa;
import com.restaurante.proyecto.entities.AdministradorRestaurante;
import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.entities.Comensal;
import com.restaurante.proyecto.repository.AdministradorEmpresaRepository;
import com.restaurante.proyecto.repository.AdministradorRestauranteRepository;
import com.restaurante.proyecto.repository.EmpleadoRepository;
import com.restaurante.proyecto.repository.ComensalRepository;

@Service
public class LoginServiceImpl {

    @Autowired
    private AdministradorEmpresaRepository adminGeneralRepo;

    @Autowired
    private AdministradorRestauranteRepository adminRestauranteRepo;

    @Autowired
    private EmpleadoRepository empleadoRepo;

    @Autowired
    private ComensalRepository comensalRepo;

    public String verificarUsuario(String email, String password) {
        // 1. Verifica en la tabla Administrador General
        AdministradorEmpresa adminGeneral = adminGeneralRepo.findByUsuarioAndPassword(email, password);
        if (adminGeneral != null) {
            return "admin_general";
        }

        // 2. Verifica en la tabla Administrador de Restaurante
        AdministradorRestaurante adminRestaurante = adminRestauranteRepo.findByCorreoAndPassword(email, password);
        if (adminRestaurante != null) {
            return "admin_restaurante";
        }

        // 3. Verifica en la tabla Empleado
        Empleado empleado = empleadoRepo.findByCorreoAndPassword(email, password);
        if (empleado != null) {
            return "empleado:" + empleado.getTipoEmpleado(); // Devolvemos el tipo de empleado también
        }

        // 4. Verifica en la tabla Comensal
        Comensal comensal = comensalRepo.findByCorreoAndPassword(email, password);
        if (comensal != null) {
            return "comensal";
        }

        // Si no se encuentra en ninguna tabla
        return null;
    }
}
