package edu.itm.estoicismo.repositoriesSQL;

import org.springframework.stereotype.Component;

@Component
public class RutaRepositoryHelper {
    // Buscar todas las Ruras
    public String listarRutas() { return "SELECT id_ruta, nombre_ruta, descripcion, nivel_dificultad FROM rutas_aprendizaje"; }
    // Buscar las Rura pot id_ruta
    public String buscarRuta(){return "SELECT id_ruta, nombre_ruta, descripcion, nivel_dificultad FROM rutas_aprendizaje WHERE id_ruta = ?";}
    // Crear Ruta
    public String obtenerUltimoId() { return "SELECT MAX(id_ruta) FROM rutas_aprendizaje"; }
    public String insertarRuta() { return "INSERT INTO rutas_aprendizaje (id_ruta, nombre_ruta, descripcion, nivel_dificultad) VALUES (?, ?, ?, ?)"; }
    // Actualizar Ruta
    public String actualizarRuta() { return "UPDATE rutas_aprendizaje SET nombre_ruta = ?, descripcion = ?, nivel_dificultad = ? WHERE id_ruta = ?"; }
    // Eliminar Ruta
    public String eliminarRuta() { return "DELETE FROM rutas_aprendizaje WHERE id_ruta = ?"; }
}