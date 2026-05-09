package edu.itm.estoicismo.services;

import edu.itm.estoicismo.entitiesSQL.Lecciones;
import edu.itm.estoicismo.repositoriesSQL.LeccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeccionService {

    private final LeccionRepository repository;

    public LeccionService(LeccionRepository repository) {
        this.repository = repository;
    }

    public List<Lecciones> listar() {
        return repository.listarLecciones();
    }

    public Lecciones crearLeccion(Lecciones leccion) {
        leccion.setIdLeccion(repository.generarNuevoIdLeccion());
        return repository.insertarLeccion(leccion);
    }

    public Lecciones actualizarLeccion(Lecciones leccion) {
        return repository.actualizarLeccion(leccion);
    }

    public boolean eliminarLeccion(int id) {
        return repository.eliminarLeccion(id);
    }

    public Lecciones buscarLeccionPorId(int id) {
        return repository.buscarLeccionPorId(id);
    }
}