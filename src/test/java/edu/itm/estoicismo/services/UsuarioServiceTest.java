package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesSQL.Usuarios;
import edu.itm.estoicismo.repositoriesSQL.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    private Usuarios usuarioMock;

    @BeforeEach
    void setUp() {
        // Inicializamos un usuario base para las pruebas
        usuarioMock = new Usuarios(
                1,
                "Marco Aurelio",
                "marco@estoico.com",
                "hash123",
                100,
                new Date()
        );
    }

    @Test
    @DisplayName("Debería retornar una lista de todos los usuarios")
    void buscarUsuarios() {
        // Arrange
        when(repository.buscarUsuarios()).thenReturn(Arrays.asList(usuarioMock));

        // Act
        List<Usuarios> resultado = service.buscarUsuarios();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Marco Aurelio", resultado.get(0).getNombreCompleto());
        verify(repository).buscarUsuarios();
    }

    @Test
    @DisplayName("Debería asignar un nuevo ID e insertar el usuario correctamente")
    void crearUsuario() {
        // Arrange
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombreCompleto("Séneca");
        nuevoUsuario.setEmail("seneca@estoico.com");

        int idGenerado = 99;
        when(repository.generarNuevoIdUsuario()).thenReturn(idGenerado);
        when(repository.insertarUsuario(any(Usuarios.class))).thenReturn(usuarioMock);

        // Act
        Usuarios resultado = service.crearUsuario(nuevoUsuario);

        // Assert
        assertNotNull(resultado);
        assertEquals(idGenerado, nuevoUsuario.getIdUsuario()); // Validamos que el servicio asignó el ID
        verify(repository).generarNuevoIdUsuario();
        verify(repository).insertarUsuario(nuevoUsuario);
    }

    @Test
    @DisplayName("Debería retornar el usuario actualizado desde el repositorio")
    void actualizarUsuario() {
        // Arrange
        when(repository.actualizarUsuario(any(Usuarios.class))).thenReturn(usuarioMock);

        // Act
        Usuarios resultado = service.actualizarUsuario(usuarioMock);

        // Assert
        assertNotNull(resultado);
        assertEquals("marco@estoico.com", resultado.getEmail());
        verify(repository).actualizarUsuario(usuarioMock);
    }

    @Test
    @DisplayName("Debería retornar true cuando el usuario es eliminado")
    void eliminarUsuario() {
        // Arrange
        int idAEliminar = 1;
        when(repository.eliminarUsuario(idAEliminar)).thenReturn(true);

        // Act
        boolean resultado = service.eliminarUsuario(idAEliminar);

        // Assert
        assertTrue(resultado);
        verify(repository).eliminarUsuario(idAEliminar);
    }

    @Test
    @DisplayName("Debería buscar y retornar un usuario por su ID")
    void buscarUsuarioId() {
        // Arrange
        int idBusqueda = 1;
        when(repository.BuscarUsuarioId(idBusqueda)).thenReturn(usuarioMock);

        // Act
        Usuarios resultado = service.buscarUsuarioId(idBusqueda);

        // Assert
        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdUsuario());
        verify(repository).BuscarUsuarioId(idBusqueda);
    }
}