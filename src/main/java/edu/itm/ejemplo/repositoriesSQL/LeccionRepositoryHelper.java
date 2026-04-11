package edu.itm.ejemplo.repositoriesSQL;

import org.springframework.stereotype.Component;

@Component
public class LeccionRepositoryHelper {

    public String listarLecciones() {
        return "SELECT id_leccion, id_ruta, titulo, contenido_teorico, orden_leccion FROM lecciones";
    }

    public String buscarLeccionPorId() {
        return "SELECT id_leccion, id_ruta, titulo, contenido_teorico, orden_leccion FROM lecciones WHERE id_leccion = ?";
    }

    public String obtenerUltimoIdLeccion() {
        return "SELECT MAX(id_leccion) FROM lecciones";
    }

    public String insertarLeccion() {
        return "INSERT INTO lecciones (id_leccion, id_ruta, titulo, contenido_teorico, orden_leccion) VALUES (?, ?, ?, ?, ?)";
    }

    public String actualizarLeccion() {
        return "UPDATE lecciones SET id_ruta = ?, titulo = ?, contenido_teorico = ?, orden_leccion = ? WHERE id_leccion = ?";
    }

    public String eliminarLeccion() {
        return "DELETE FROM lecciones WHERE id_leccion = ?";
    }
}