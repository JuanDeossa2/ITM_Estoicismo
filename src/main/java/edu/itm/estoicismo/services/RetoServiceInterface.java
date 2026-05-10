package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesJPA.RetoDiario;
import edu.itm.estoicismo.entitiesJPA.RegistroReto;
import java.util.List;
import java.util.Optional;

public interface RetoServiceInterface {
    // Retos Diarios
    List<RetoDiario> listarRetos();
    RetoDiario guardarReto(RetoDiario reto);
    RetoDiario actualizarReto(RetoDiario reto);
    Optional<RetoDiario> buscarRetoPorId(Integer id);
    void eliminarReto(Integer id);

    // Registro de Retos
    RegistroReto registrarCumplimiento(RegistroReto registro);
    RegistroReto actualizarRegistro(RegistroReto registro);
    List<RegistroReto> obtenerHistorialUsuario(Integer idUsuario);
    Optional<RegistroReto> buscarRegistroPorId(Integer id);
    void eliminarRegistro(Integer id);
}