package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesSQL.RutaAprendizaje;
import edu.itm.estoicismo.repositoriesSQL.RutaRepository;
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
class RutaServiceTest {

    @Mock
    private RutaRepository repository;

    @InjectMocks
    private RutaService service;

    private RutaAprendizaje rutaMock;

    @BeforeEach
    void setUp() {
        // Datos de prueba reutilizables
        rutaMock = new RutaAprendizaje(1, "Ruta Estoica", "Principios básicos", "Principiante");
    }

    @Test
    @DisplayName("Debería retornar una lista con todas las rutas de aprendizaje")
    void listar() {
        // Arrange
        when(repository.BuscarRutas()).thenReturn(Arrays.asList(rutaMock));

        // Act
        List<RutaAprendizaje> resultado = service.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ruta Estoica", resultado.get(0).getNombreRuta());
        verify(repository).BuscarRutas();
    }

    @Test
    @DisplayName("Debería generar un nuevo ID y guardar la ruta")
    void crear() {
        // Arrange
        RutaAprendizaje nuevaRuta = new RutaAprendizaje();
        nuevaRuta.setNombreRuta("Nueva Ruta");

        int idEsperado = 5;
        when(repository.generarNuevoId()).thenReturn(idEsperado);
        when(repository.insertar(any(RutaAprendizaje.class))).thenReturn(rutaMock);

        // Act
        RutaAprendizaje resultado = service.crear(nuevaRuta);

        // Assert
        assertNotNull(resultado);
        assertEquals(idEsperado, nuevaRuta.getIdRuta()); // Verifica que el service asignó el ID
        verify(repository).generarNuevoId();
        verify(repository).insertar(nuevaRuta);
    }

    @Test
    @DisplayName("Debería retornar la ruta con los datos actualizados")
    void actualizar() {
        // Arrange
        when(repository.actualizar(any(RutaAprendizaje.class))).thenReturn(rutaMock);

        // Act
        RutaAprendizaje resultado = service.actualizar(rutaMock);

        // Assert
        assertNotNull(resultado);
        assertEquals("Ruta Estoica", resultado.getNombreRuta());
        verify(repository).actualizar(rutaMock);
    }

    @Test
    @DisplayName("Debería retornar true si la ruta fue eliminada correctamente")
    void eliminar() {
        // Arrange
        int idEliminar = 1;
        when(repository.eliminar(idEliminar)).thenReturn(true);

        // Act
        boolean resultado = service.eliminar(idEliminar);

        // Assert
        assertTrue(resultado);
        verify(repository).eliminar(idEliminar);
    }

    @Test
    @DisplayName("Debería encontrar una ruta específica por su ID")
    void buscarRutaId() {
        // Arrange
        int idBusqueda = 1;
        when(repository.BuscarRutaId(idBusqueda)).thenReturn(rutaMock);

        // Act
        RutaAprendizaje resultado = service.buscarRutaId(idBusqueda);

        // Assert
        assertNotNull(resultado);
        assertEquals("Ruta Estoica", resultado.getNombreRuta());
        verify(repository).BuscarRutaId(idBusqueda);
    }
}