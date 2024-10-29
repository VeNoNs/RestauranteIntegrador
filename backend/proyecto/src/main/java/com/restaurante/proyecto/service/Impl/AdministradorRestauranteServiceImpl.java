package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.AdministradorRestaurante;
import com.restaurante.proyecto.repository.AdministradorRestauranteRepository;
import com.restaurante.proyecto.service.AdministradorRestauranteService;

@Service
public class AdministradorRestauranteServiceImpl implements AdministradorRestauranteService {

    @Autowired
    private AdministradorRestauranteRepository adminRestauranteRepository;

    @Override
    public List<AdministradorRestaurante> obtenerTodos() {
        return adminRestauranteRepository.findAll();
    }

    @Override
    public AdministradorRestaurante obtenerPorId(Long id) {
        return adminRestauranteRepository.findById(id).orElse(null);
    }

    @Override
    public AdministradorRestaurante crearAdministradorRestaurante(AdministradorRestaurante adminRestaurante) {
        return adminRestauranteRepository.save(adminRestaurante);
    }

    @Override
    public AdministradorRestaurante actualizarAdministradorRestaurante(Long id, AdministradorRestaurante adminRestaurante) {
        AdministradorRestaurante adminRestauranteBD = adminRestauranteRepository.findById(id).orElse(null);

        if (adminRestauranteBD != null) {
            adminRestauranteBD.setCorreo(adminRestaurante.getCorreo());
            adminRestauranteBD.setPassword(adminRestaurante.getPassword());
            adminRestauranteBD.setEmpresa(adminRestaurante.getEmpresa());
            return adminRestauranteRepository.save(adminRestauranteBD);
        }
        return null;
    }

    @Override
    public void eliminarAdministradorRestaurante(Long id) {
        adminRestauranteRepository.deleteById(id);
    }

    @Override
    public long contarAdministradorRestaurante() {
        return adminRestauranteRepository.count();
    }
}
