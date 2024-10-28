package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Comensal;
import com.restaurante.proyecto.repository.ComensalRepository;
import com.restaurante.proyecto.service.ComensalService;

/**
 * Implementación del servicio para manejar operaciones relacionadas con la entidad Comensal.
 * Proporciona métodos para obtener, crear, actualizar y eliminar comensales.
 */
@Service
public class ComensalServiceImpl implements ComensalService {
    
    @Autowired
    private ComensalRepository comensalRepository;

    /**
     * Obtiene una lista de todos los comensales.
     *
     * @return Lista de objetos Comensal.
     */
    @Override
    public List<Comensal> obtenerTodos() {
        return comensalRepository.findAll();
    }

    /**
     * Obtiene un comensal por su ID.
     *
     * @param id El ID del comensal.
     * @return El objeto Comensal correspondiente o null si no se encuentra.
     */
    @Override
    public Comensal obtenerPorId(Long id) {
        return comensalRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo comensal.
     *
     * @param comensal El objeto Comensal a crear.
     * @return El objeto Comensal creado.
     */
    @Override
    public Comensal crearComensal(Comensal comensal) {
        return comensalRepository.save(comensal);
    }

    /**
     * Actualiza un comensal existente.
     *
     * @param id El ID del comensal a actualizar.
     * @param comensal El objeto Comensal con la nueva información.
     * @return El objeto Comensal actualizado o null si no se encuentra el comensal.
     */
    @Override
    public Comensal actualizarComensal(Long id, Comensal comensal) {
        Comensal comensalBD = comensalRepository.findById(id).orElse(null);

        if (comensalBD != null) {
            comensalBD.setNombreComensal(comensal.getNombreComensal());
            comensalBD.setApellidoComensal(comensal.getApellidoComensal());
            comensalBD.setCorreo(comensal.getCorreo());
            return comensalRepository.save(comensalBD);
        }
        return null;
    }

    /**
     * Elimina un comensal por su ID.
     *
     * @param id El ID del comensal a eliminar.
     */
    @Override
    public void eliminarComensal(Long id) {
        comensalRepository.deleteById(id);
    }

    /**
     * Cuenta el número total de comensales.
     *
     * @return El número total de comensales.
     */
    @Override
    public long contarComensal() {
        return comensalRepository.count();
    }
}
