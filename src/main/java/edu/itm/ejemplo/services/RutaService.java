package edu.itm.ejemplo.services;

import edu.itm.ejemplo.entitiesSQL.RutaAprendizaje;
import edu.itm.ejemplo.repositoriesSQL.RutaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RutaService {
    private final RutaRepository repository;

    public RutaService(RutaRepository repository) { this.repository = repository; }

    public List<RutaAprendizaje> listar() { return repository.BuscarRutas(); }

    public RutaAprendizaje crear(RutaAprendizaje ruta) {
        ruta.setIdRuta(repository.generarNuevoId());
        return repository.insertar(ruta);
    }
    public RutaAprendizaje actualizar(RutaAprendizaje ruta) {
        return repository.actualizar(ruta);
    }

    public boolean eliminar(int idRuta) {
        return repository.eliminar(idRuta);
    }

    public RutaAprendizaje buscarRutaId(int id) {
        return repository.BuscarRutaId(id);
    }
}
