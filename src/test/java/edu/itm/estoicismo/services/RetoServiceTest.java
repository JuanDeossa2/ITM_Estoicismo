package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesJPA.RegistroReto;
import edu.itm.estoicismo.entitiesJPA.RetoDiario;
import edu.itm.estoicismo.repositoriesJPA.RegistroRetoRepository;
import edu.itm.estoicismo.repositoriesJPA.RetoDiarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetoServiceTest {

    @Mock
    private RetoDiarioRepository retoRepo;

    @Mock
    private RegistroRetoRepository registroRepo;

    @InjectMocks
    private RetoService retoService;

    private RetoDiario retoEjemplo;
    private RegistroReto registroEjemplo;

    @BeforeEach
    void setUp() {
        retoEjemplo = new RetoDiario();
        retoEjemplo.setIdReto(1);
        retoEjemplo.setDescripcionReto("Practicar la dicotomía del control");
        retoEjemplo.setPuntosRecompensa(15);

        registroEjemplo = new RegistroReto();
        registroEjemplo.setIdRegistro(10);
        registroEjemplo.setEstado(RegistroReto.EstadoReto.Completado);
        registroEjemplo.setFechaCumplimiento(LocalDate.now());
    }

    // --- PRUEBAS DE RETOS DIARIOS ---

    @Test
    void listarRetos() {
        when(retoRepo.findAll()).thenReturn(List.of(retoEjemplo));

        List<RetoDiario> resultado = retoService.listarRetos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(retoRepo, times(1)).findAll();
    }

    @Test
    void guardarReto() {
        when(retoRepo.save(any(RetoDiario.class))).thenReturn(retoEjemplo);

        RetoDiario guardado = retoService.guardarReto(new RetoDiario());

        assertNotNull(guardado);
        assertEquals("Practicar la dicotomía del control", guardado.getDescripcionReto());
        verify(retoRepo).save(any());
    }

    @Test
    void buscarRetoPorId() {
        when(retoRepo.findById(1)).thenReturn(Optional.of(retoEjemplo));

        Optional<RetoDiario> encontrado = retoService.buscarRetoPorId(1);

        assertTrue(encontrado.isPresent());
        assertEquals(1, encontrado.get().getIdReto());
    }

    @Test
    void eliminarReto() {
        doNothing().when(retoRepo).deleteById(1);

        retoService.eliminarReto(1);

        verify(retoRepo, times(1)).deleteById(1);
    }

    // --- PRUEBAS DE REGISTRO DE RETOS ---

    @Test
    void registrarCumplimiento() {
        when(registroRepo.save(any(RegistroReto.class))).thenReturn(registroEjemplo);

        RegistroReto resultado = retoService.registrarCumplimiento(new RegistroReto());

        assertNotNull(resultado);
        assertEquals(RegistroReto.EstadoReto.Completado, resultado.getEstado());
        verify(registroRepo).save(any());
    }

    @Test
    void obtenerHistorialUsuario() {
        when(registroRepo.findByUsuarioIdUsuario(1)).thenReturn(List.of(registroEjemplo));

        List<RegistroReto> historial = retoService.obtenerHistorialUsuario(1);

        assertFalse(historial.isEmpty());
        assertEquals(1, historial.size());
        verify(registroRepo).findByUsuarioIdUsuario(1);
    }

    @Test
    void buscarRegistroPorId() {
        when(registroRepo.findById(10)).thenReturn(Optional.of(registroEjemplo));

        Optional<RegistroReto> encontrado = retoService.buscarRegistroPorId(10);

        assertTrue(encontrado.isPresent());
        assertEquals(10, encontrado.get().getIdRegistro());
    }

    @Test
    void eliminarRegistro() {
        doNothing().when(registroRepo).deleteById(10);

        retoService.eliminarRegistro(10);

        verify(registroRepo, times(1)).deleteById(10);
    }
}