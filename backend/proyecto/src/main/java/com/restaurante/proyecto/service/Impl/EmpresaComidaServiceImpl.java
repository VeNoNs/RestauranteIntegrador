package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.EmpresaComida;
import com.restaurante.proyecto.repository.EmpresaComidaRepository;
import com.restaurante.proyecto.service.EmpresaComidaService;

/**
 * Implementación del servicio para manejar operaciones relacionadas con la entidad EmpresaComida.
 * Proporciona métodos para obtener, crear, actualizar y eliminar empresas de comida.
 */
@Service
public class EmpresaComidaServiceImpl implements EmpresaComidaService {
    
    @Autowired
    private EmpresaComidaRepository empresaComidaRepository;

    /**
     * Obtiene una lista de todas las empresas de comida.
     *
     * @return Lista de objetos EmpresaComida.
     */
    @Override
    public List<EmpresaComida> obtenerTodos() {
        return empresaComidaRepository.findAll();
    }

    /**
     * Obtiene una empresa de comida por su ID.
     *
     * @param id El ID de la empresa.
     * @return El objeto EmpresaComida correspondiente, o null si no se encuentra.
     */
    @Override
    public EmpresaComida obtenerPorId(Long id) {
        return empresaComidaRepository.findById(id).orElse(null);
    }

    /**
     * Crea una nueva empresa de comida.
     *
     * @param empresa El objeto EmpresaComida a crear.
     * @return El objeto EmpresaComida creado.
     */
    @Override
    public EmpresaComida crearEmpresa(EmpresaComida empresa) {
        return empresaComidaRepository.save(empresa);
    }

    /**
     * Actualiza una empresa de comida existente.
     *
     * @param id El ID de la empresa a actualizar.
     * @param empresa El objeto EmpresaComida con la nueva información.
     * @return El objeto EmpresaComida actualizado, o null si no se encuentra la empresa.
     */
    @Override
    public EmpresaComida actualizarEmpresa(Long id, EmpresaComida empresa) {
        EmpresaComida empresaBD = empresaComidaRepository.findById(id).orElse(null);

        if (empresaBD != null) {
            empresaBD.setUbicacion(empresa.getUbicacion());
            return empresaComidaRepository.save(empresaBD);
        }
        return null;
    }

    /**
     * Elimina una empresa de comida por su ID.
     *
     * @param id El ID de la empresa a eliminar.
     */
    @Override
    public void eliminarEmpresa(Long id) {
        empresaComidaRepository.deleteById(id);
    }

    /**
     * Cuenta el número total de empresas de comida.
     *
     * @return El número total de empresas de comida.
     */
    @Override
    public long contarEmpresa() {
        return empresaComidaRepository.count();
    }
}
