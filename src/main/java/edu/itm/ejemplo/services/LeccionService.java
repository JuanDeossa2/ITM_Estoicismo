package edu.itm.ejemplo.services;

import edu.itm.ejemplo.entitiesSQL.Lecciones;
import edu.itm.ejemplo.repositoriesSQL.LeccionRepository;
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