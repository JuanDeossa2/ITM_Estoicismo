package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesSQL.ProgresoLecciones;
import edu.itm.estoicismo.repositoriesSQL.ProgresoLeccionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProgresoLeccionService {

    private final ProgresoLeccionRepository repository;

    public ProgresoLeccionService(ProgresoLeccionRepository repository) {
        this.repository = repository;
    }

    public List<ProgresoLecciones> listarProgreso() {
        return repository.listarProgreso();
    }

    public ProgresoLecciones crearProgreso(ProgresoLecciones progreso) {
        progreso.setIdProgreso(repository.generarNuevoId());
        progreso.setCompletadaEn(new Timestamp(System.currentTimeMillis()));
        return repository.insertar(progreso);
    }

    public boolean eliminarProgreso(int idProgreso) {
        return repository.eliminar(idProgreso);
    }

    public ProgresoLecciones buscarPorIdProgreso(int id) {
        return repository.buscarPorId(id);
    }

    public ProgresoLecciones actualizarProgreso(ProgresoLecciones progreso) {
        return repository.actualizar(progreso);
    }
}