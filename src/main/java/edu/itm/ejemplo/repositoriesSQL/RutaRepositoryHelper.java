package edu.itm.ejemplo.repositoriesSQL;

import org.springframework.stereotype.Component;

@Component
public class RutaRepositoryHelper {
    public String listarRutas() { return "SELECT id_ruta, nombre_ruta, descripcion, nivel_dificultad FROM rutas_aprendizaje"; }
    public String insertarRuta() { return "INSERT INTO rutas_aprendizaje (id_ruta, nombre_ruta, descripcion, nivel_dificultad) VALUES (?, ?, ?, ?)"; }
    public String obtenerUltimoId() { return "SELECT MAX(id_ruta) FROM rutas_aprendizaje"; }
    public String actualizarRuta() { return "UPDATE rutas_aprendizaje SET nombre_ruta = ?, descripcion = ?, nivel_dificultad = ? WHERE id_ruta = ?"; }
    public String eliminarRuta() { return "DELETE FROM rutas_aprendizaje WHERE id_ruta = ?"; }
}