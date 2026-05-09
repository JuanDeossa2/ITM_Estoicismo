package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesSQL.ProgresoLecciones;
import edu.itm.estoicismo.repositoriesSQL.ProgresoLeccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProgresoLeccionServiceTest {

    @Mock
    private ProgresoLeccionRepository repository;

    @InjectMocks
    private ProgresoLeccionService service;

    private ProgresoLecciones progresoMock;

    @BeforeEach
    void setUp() {
        // Objeto de prueba base
        progresoMock = new ProgresoLecciones(1, 100, 5, new Timestamp(System.currentTimeMillis()));
    }

    @Test
    @DisplayName("Debería retornar la lista completa de progresos")
    void listarProgreso() {
        // Arrange
        when(repository.listarProgreso()).thenReturn(Arrays.asList(progresoMock));

        // Act
        List<ProgresoLecciones> resultado = service.listarProgreso();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(repository).listarProgreso();
    }

    @Test
    @DisplayName("Debería asignar ID y Timestamp actual al crear un progreso")
    void crearProgreso() {
        // Arrange
        ProgresoLecciones nuevo = new ProgresoLecciones();
        nuevo.setIdUsuario(100);
        nuevo.setIdLeccion(5);

        int idGenerado = 10;
        when(repository.generarNuevoId()).thenReturn(idGenerado);
        // Usamos any() porque el objeto cambiará internamente (se le setea ID y fecha)
        when(repository.insertar(any(ProgresoLecciones.class))).thenReturn(progresoMock);

        // Act
        ProgresoLecciones resultado = service.crearProgreso(nuevo);

        // Assert
        assertNotNull(resultado);
        assertEquals(idGenerado, nuevo.getIdProgreso());
        assertNotNull(nuevo.getCompletadaEn()); // Verifica que el service puso la fecha
        verify(repository).generarNuevoId();
        verify(repository).insertar(nuevo);
    }

    @Test
    @DisplayName("Debería retornar true si el repositorio elimina el registro")
    void eliminarProgreso() {
        // Arrange
        int idEliminar = 1;
        when(repository.eliminar(idEliminar)).thenReturn(true);

        // Act
        boolean resultado = service.eliminarProgreso(idEliminar);

        // Assert
        assertTrue(resultado);
        verify(repository).eliminar(idEliminar);
    }

    @Test
    @DisplayName("Debería buscar un progreso específico por su ID")
    void buscarPorIdProgreso() {
        // Arrange
        int idBusqueda = 1;
        when(repository.buscarPorId(idBusqueda)).thenReturn(progresoMock);

        // Act
        ProgresoLecciones resultado = service.buscarPorIdProgreso(idBusqueda);

        // Assert
        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdProgreso());
        verify(repository).buscarPorId(idBusqueda);
    }

    @Test
    @DisplayName("Debería actualizar los datos de un progreso existente")
    void actualizarProgreso() {
        // Arrange
        when(repository.actualizar(any(ProgresoLecciones.class))).thenReturn(progresoMock);

        // Act
        ProgresoLecciones resultado = service.actualizarProgreso(progresoMock);

        // Assert
        assertNotNull(resultado);
        verify(repository).actualizar(progresoMock);
    }
}