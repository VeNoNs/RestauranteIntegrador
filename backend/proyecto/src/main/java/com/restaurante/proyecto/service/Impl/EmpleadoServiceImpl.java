package com.restaurante.proyecto.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurante.proyecto.entities.Empleado;
import com.restaurante.proyecto.repository.EmpleadoRepository;
import com.restaurante.proyecto.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

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

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public long contarEmpleado() {
        return empleadoRepository.count();
    }
}
