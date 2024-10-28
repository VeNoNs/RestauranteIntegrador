package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.repository.EmpleadoRepository;
import com.restaurante.proyecto.service.EmpleadoService;

/**
 * Implementación del servicio para manejar operaciones relacionadas con la entidad Empleado.
 * Proporciona métodos para obtener, crear, actualizar y eliminar empleados.
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    /**
     * Obtiene una lista de todos los empleados.
     *
     * @return Lista de objetos Empleado.
     */
    @Override
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param id El ID del empleado.
     * @return El objeto Empleado correspondiente, o null si no se encuentra.
     */
    @Override
    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo empleado.
     *
     * @param empleado El objeto Empleado a crear.
     * @return El objeto Empleado creado.
     */
    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    /**
     * Actualiza un empleado existente.
     *
     * @param id El ID del empleado a actualizar.
     * @param empleado El objeto Empleado con la nueva información.
     * @return El objeto Empleado actualizado, o null si no se encuentra el empleado.
     */
    @Override
    public Empleado actualizarEmpleado(Long id, Empleado empleado) {
        Empleado empleadoBD = empleadoRepository.findById(id).orElse(null);

        if (empleadoBD != null) {
            empleadoBD.setNombreEmpleado(empleado.getNombreEmpleado());
            empleadoBD.setApellidoEmpleado(empleado.getApellidoEmpleado());
            empleadoBD.setTipoEmpleado(empleado.getTipoEmpleado());
            return empleadoRepository.save(empleadoBD);
        }
        return null;
    }

    /**
     * Elimina un empleado por su ID.
     *
     * @param id El ID del empleado a eliminar.
     */
    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    /**
     * Cuenta el número total de empleados.
     *
     * @return El número total de empleados.
     */
    @Override
    public long contarEmpleado() {
        return empleadoRepository.count();
    }
}
