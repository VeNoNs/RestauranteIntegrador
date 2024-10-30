package com.restaurante.proyecto.service;

import java.util.List;
import com.restaurante.proyecto.entities.Empleado;
/**
 * Interfaz que define los servicios para la gestión de {@code Empleado}.
 */
public interface EmpleadoService {
    
    List<Empleado> obtenerTodos();

    Empleado obtenerPorId(Long id);

    Empleado crearEmpleado(Empleado empleado);

    Empleado actualizarEmpleado(Long id, Empleado empleado);

    void eliminarEmpleado(Long id);

    long contarEmpleado();
}
