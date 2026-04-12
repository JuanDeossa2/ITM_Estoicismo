package edu.itm.ejemplo.repositoriesSQL;

import org.springframework.stereotype.Component;

@Component
public class ProgresoLeccionRepositoryHelper {

    public String listarProgreso() {
        return "SELECT id_progreso, id_usuario, id_leccion, completada_en FROM progreso_lecciones";
    }

    public String buscarPorIdProgreso() {
        return "SELECT id_progreso, id_usuario, id_leccion, completada_en FROM progreso_lecciones WHERE id_progreso = ?";
    }

    public String obtenerUltimoIdProgreso() {
        return "SELECT MAX(id_progreso) FROM progreso_lecciones";
    }

    public String insertarProgreso() {
        return "INSERT INTO progreso_lecciones (id_progreso, id_usuario, id_leccion, completada_en) VALUES (?, ?, ?, ?)";
    }

    public String eliminarProgreso() {
        return "DELETE FROM progreso_lecciones WHERE id_progreso = ?";
    }

    public String actualizar() {
        return "UPDATE progreso_lecciones SET id_usuario = ?, id_leccion = ?, completada_en = ? WHERE id_progreso = ?";
    }
}