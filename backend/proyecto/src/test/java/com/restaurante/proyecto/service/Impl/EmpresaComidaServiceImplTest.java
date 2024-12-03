package com.restaurante.proyecto.service.Impl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.restaurante.proyecto.entities.Comida;
import com.restaurante.proyecto.entities.EmpresaComida;
import com.restaurante.proyecto.repository.ComidaRepository;
import com.restaurante.proyecto.repository.EmpresaComidaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmpresaComidaServiceImplTest {
    @Mock
    private EmpresaComidaRepository empresaComidaRepository;
    private ComidaRepository comidaRepository;

    @InjectMocks
    private EmpresaComidaServiceImpl empresaComidaService;
    private ComidaServiceImpl comidaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testObtenerTodos() {
        // Arrange
        EmpresaComida empresa1 = new EmpresaComida(1L, "Ubicación 1");
        EmpresaComida empresa2 = new EmpresaComida(2L, "Ubicación 2");
        when(empresaComidaRepository.findAll()).thenReturn(Arrays.asList(empresa1, empresa2));

        // Act
        var empresas = empresaComidaService.obtenerTodos();

        // Assert
        assertEquals(2, empresas.size());
        assertEquals("Ubicación 1", empresas.get(0).getUbicacion());
        assertEquals("Ubicación 2", empresas.get(1).getUbicacion());
        verify(empresaComidaRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        // Arrange
        EmpresaComida empresa = new EmpresaComida(1L, "Ubicación Test");
        when(empresaComidaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        // Act
        EmpresaComida result = empresaComidaService.obtenerPorId(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Ubicación Test", result.getUbicacion());
        verify(empresaComidaRepository, times(1)).findById(1L);
    }

    @Test
    void testCrearEmpresa() {
        // Arrange
        EmpresaComida nuevaEmpresa = new EmpresaComida(null, "Nueva Ubicación");
        EmpresaComida empresaGuardada = new EmpresaComida(1L, "Nueva Ubicación");
        when(empresaComidaRepository.save(nuevaEmpresa)).thenReturn(empresaGuardada);

        // Act
        EmpresaComida result = empresaComidaService.crearEmpresa(nuevaEmpresa);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdEmpresa());
        assertEquals("Nueva Ubicación", result.getUbicacion());
        verify(empresaComidaRepository, times(1)).save(nuevaEmpresa);
    }

    @Test
    void testActualizarEmpresa() {
        // Arrange
        EmpresaComida empresaExistente = new EmpresaComida(1L, "Ubicación Anterior");
        EmpresaComida empresaActualizada = new EmpresaComida(null, "Ubicación Nueva");
        when(empresaComidaRepository.findById(1L)).thenReturn(Optional.of(empresaExistente));
        when(empresaComidaRepository.save(any(EmpresaComida.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        EmpresaComida result = empresaComidaService.actualizarEmpresa(1L, empresaActualizada);

        // Assert
        assertNotNull(result);
        assertEquals("Ubicación Nueva", result.getUbicacion());
        verify(empresaComidaRepository, times(1)).findById(1L);
        verify(empresaComidaRepository, times(1)).save(empresaExistente);
    }

    @Test
    void testEliminarEmpresa() {
        // Arrange
        Long idEmpresa = 1L;
        doNothing().when(empresaComidaRepository).deleteById(idEmpresa);

        // Act
        empresaComidaService.eliminarEmpresa(idEmpresa);

        // Assert
        verify(empresaComidaRepository, times(1)).deleteById(idEmpresa);
    }

    @Test
    void testContarEmpresa() {
        // Arrange
        when(empresaComidaRepository.count()).thenReturn(5L);

        // Act
        long count = empresaComidaService.contarEmpresa();

        // Assert
        assertEquals(5L, count);
        verify(empresaComidaRepository, times(1)).count();
    }

    @Test
    void testObtenerTodos() {
        // Datos de prueba
        List<Comida> comidasMock = List.of(
                new Comida(1L, "Pizza",
                        "Italiana", "Pizza con queso y tomate", 20.0),
                new Comida(2L, "Sushi",
                        "Japonesa", "Rollos de sushi con salmón", 25.0)
        );

        // Simulación del repositorio
        when(comidaRepository.findAll()).thenReturn(comidasMock);

        // Ejecución
        List<Comida> resultado = comidaService.obtenerTodos();

        // Validaciones
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Pizza", resultado.get(0).getNombreComida());
        verify(comidaRepository, times(1)).findAll();
    }

    @Test
    void testCrearComida() {
        // Datos de entrada y resultado esperado
        Comida comidaNueva = new Comida(null, "Tacos",
                "Mexicana", "Tacos de carne asada", 15.0);
        Comida comidaGuardada = new Comida(1L, "Tacos",
                "Mexicana", "Tacos de carne asada", 15.0);

        // Simulación del repositorio
        when(comidaRepository.save(comidaNueva)).thenReturn(comidaGuardada);

        // Ejecución
        Comida resultado = comidaService.crearComida(comidaNueva);

        // Validaciones
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdComida());
        assertEquals("Tacos", resultado.getNombreComida());
        verify(comidaRepository, times(1)).save(comidaNueva);
    }

    @Test
    void testActualizarComida() {
        // Datos de prueba
        Comida comidaExistente = new Comida(1L, "Pizza",
                "Italiana", "Pizza con queso y tomate", 20.0);
        Comida comidaActualizada = new Comida(1L, "Pizza Margarita",
                "Italiana", "Pizza con queso y albahaca", 22.0);

        // Simulación del repositorio
        when(comidaRepository.findById(1L)).thenReturn(Optional.of(comidaExistente));
        when(comidaRepository.save(any(Comida.class))).thenReturn(comidaActualizada);

        // Ejecución
        Comida resultado = comidaService.actualizarComida(1L, comidaActualizada);

        // Validaciones
        assertNotNull(resultado);
        assertEquals("Pizza Margarita", resultado.getNombreComida());
        assertEquals(22.0, resultado.getPrecio());
        verify(comidaRepository, times(1)).findById(1L);
        verify(comidaRepository, times(1)).save(comidaActualizada);
    }

    @Test
    void testEliminarComida() {
        // Simulación del repositorio
        doNothing().when(comidaRepository).deleteById(1L);

        // Ejecución
        comidaService.eliminarComida(1L);

        // Validaciones
        verify(comidaRepository, times(1)).deleteById(1L);
    }

}
