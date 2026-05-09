package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesSQL.Lecciones;
import edu.itm.estoicismo.repositoriesSQL.LeccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeccionServiceTest {

    @Mock
    private LeccionRepository repository;

    @InjectMocks
    private LeccionService service;

    private Lecciones leccionEjemplo;

    @BeforeEach
    void setUp() {
        leccionEjemplo = new Lecciones(1, 10, "Introducción", "Contenido...", 1);
    }

    @Test
    @DisplayName("Debería retornar una lista de lecciones")
    void listar() {
        // Arrange
        List<Lecciones> listaMock = Arrays.asList(leccionEjemplo);
        when(repository.listarLecciones()).thenReturn(listaMock);

        // Act
        List<Lecciones> resultado = service.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(repository, times(1)).listarLecciones();
    }

    @Test
    @DisplayName("Debería asignar un nuevo ID e insertar la lección")
    void crearLeccion() {
        // Arrange
        Lecciones nuevaLeccion = new Lecciones(0, 10, "Nueva", "...", 2);
        when(repository.generarNuevoIdLeccion()).thenReturn(5);
        when(repository.insertarLeccion(any(Lecciones.class))).thenReturn(leccionEjemplo);

        // Act
        Lecciones resultado = service.crearLeccion(nuevaLeccion);

        // Assert
        assertNotNull(resultado);
        verify(repository).generarNuevoIdLeccion();
        verify(repository).insertarLeccion(nuevaLeccion);
        // Verificamos que al objeto original se le seteo el ID generado
        assertEquals(5, nuevaLeccion.getIdLeccion());
    }

    @Test
    @DisplayName("Debería retornar la lección actualizada")
    void actualizarLeccion() {
        // Arrange
        when(repository.actualizarLeccion(any(Lecciones.class))).thenReturn(leccionEjemplo);

        // Act
        Lecciones resultado = service.actualizarLeccion(leccionEjemplo);

        // Assert
        assertNotNull(resultado);
        assertEquals("Introducción", resultado.getTitulo());
        verify(repository).actualizarLeccion(leccionEjemplo);
    }

    @Test
    @DisplayName("Debería retornar true cuando se elimina correctamente")
    void eliminarLeccion() {
        // Arrange
        int idAEliminar = 1;
        when(repository.eliminarLeccion(idAEliminar)).thenReturn(true);

        // Act
        boolean resultado = service.eliminarLeccion(idAEliminar);

        // Assert
        assertTrue(resultado);
        verify(repository).eliminarLeccion(idAEliminar);
    }

    @Test
    @DisplayName("Debería retornar una lección cuando se busca por ID")
    void buscarLeccionPorId() {
        // Arrange
        int idABuscar = 1;
        when(repository.buscarLeccionPorId(idABuscar)).thenReturn(leccionEjemplo);

        // Act
        Lecciones resultado = service.buscarLeccionPorId(idABuscar);

        // Assert
        assertNotNull(resultado);
        assertEquals(idABuscar, resultado.getIdLeccion());
        verify(repository).buscarLeccionPorId(idABuscar);
    }
}