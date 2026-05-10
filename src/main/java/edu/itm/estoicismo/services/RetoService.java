package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesJPA.RetoDiario;
import edu.itm.estoicismo.entitiesJPA.RegistroReto;
import edu.itm.estoicismo.repositoriesJPA.RetoDiarioRepository;
import edu.itm.estoicismo.repositoriesJPA.RegistroRetoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RetoService implements RetoServiceInterface {
    private final RetoDiarioRepository retoRepo;
    private final RegistroRetoRepository registroRepo;

    public RetoService(RetoDiarioRepository retoRepo, RegistroRetoRepository registroRepo) {
        this.retoRepo = retoRepo;
        this.registroRepo = registroRepo;
    }

    @Override
    public List<RetoDiario> listarRetos() {
        return retoRepo.findAll();
    }

    @Override
    public RetoDiario guardarReto(RetoDiario reto) {
        return retoRepo.save(reto);
    }

    @Override
    public RetoDiario actualizarReto(RetoDiario reto) {
        return retoRepo.save(reto);
    }

    @Override
    public Optional<RetoDiario> buscarRetoPorId(Integer id) {
        return retoRepo.findById(id);
    }

    @Override
    public void eliminarReto(Integer id) {
        retoRepo.deleteById(id);
    }

    @Override
    public RegistroReto registrarCumplimiento(RegistroReto registro) {
        return registroRepo.save(registro);
    }

    @Override
    public RegistroReto actualizarRegistro(RegistroReto registro) {
        return registroRepo.save(registro);
    }

    @Override
    public List<RegistroReto> obtenerHistorialUsuario(Integer idUsuario) {
        return registroRepo.findByUsuarioIdUsuario(idUsuario);
    }

    @Override
    public Optional<RegistroReto> buscarRegistroPorId(Integer id) {
        return registroRepo.findById(id);
    }

    @Override
    public void eliminarRegistro(Integer id) {
        registroRepo.deleteById(id);
    }
}