package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Local;
import com.restaurante.proyecto.repository.LocalRepository;
import com.restaurante.proyecto.service.LocalService;

/**
 * Implementación del servicio para gestionar locales.
 * Proporciona métodos para realizar operaciones CRUD en locales.
 */
@Service
public class LocalServiceImpl implements LocalService {
    
    @Autowired
    private LocalRepository localRepository;

    /**
     * Obtiene todos los locales.
     *
     * @return Lista de todos los locales disponibles.
     */
    @Override
    public List<Local> obtenerTodos() {
        return localRepository.findAll();
    }

    /**
     * Obtiene un local por su ID.
     *
     * @param id El ID del local a buscar.
     * @return El local correspondiente al ID, o null si no se encuentra.
     */
    @Override
    public Local obtenerPorId(Long id) {
        return localRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo local.
     *
     * @param local El objeto Local a crear.
     * @return El local creado.
     */
    @Override
    public Local crearLocal(Local local) {
        return localRepository.save(local);
    }

    /**
     * Actualiza un local existente.
     *
     * @param id El ID del local a actualizar.
     * @param local El objeto Local con los nuevos datos.
     * @return El local actualizado, o null si el local no existe.
     */
    @Override
    public Local actualizarLocal(Long id, Local local) {
        Local localBD = localRepository.findById(id).orElse(null);

        if (localBD != null) {
            localBD.setNroMesa(local.getNroMesa());
            return localRepository.save(localBD);
        }
        return null;
    }

    /**
     * Elimina un local por su ID.
     *
     * @param id El ID del local a eliminar.
     */
    @Override
    public void eliminarLocal(Long id) {
        localRepository.deleteById(id);
    }

    /**
     * Cuenta el número total de locales.
     *
     * @return El número total de locales.
     */
    @Override
    public long contarLocal() {
        return localRepository.count();
    }
}
