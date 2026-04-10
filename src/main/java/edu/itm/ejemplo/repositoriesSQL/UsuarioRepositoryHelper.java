package edu.itm.ejemplo.repositoriesSQL;

import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryHelper {
    public String listarUsuarios() { return "SELECT id_usuario, nombre_completo, email, password_hash, puntos_totales, fecha_registro FROM usuarios"; }
    public String insertarRuta() { return "INSERT INTO rutas_aprendizaje (id_ruta, nombre_ruta, descripcion, nivel_dificultad) VALUES (?, ?, ?, ?)"; }
    public String obtenerUltimoId() { return "SELECT MAX(id_ruta) FROM rutas_aprendizaje"; }
    public String actualizarRuta() { return "UPDATE rutas_aprendizaje SET nombre_ruta = ?, descripcion = ?, nivel_dificultad = ? WHERE id_ruta = ?"; }
    public String eliminarRuta() { return "DELETE FROM rutas_aprendizaje WHERE id_ruta = ?"; }
}
/*
CREATE TABLE usuarios (
        id_usuario INT AUTO_INCREMENT PRIMARY KEY,
        nombre_completo VARCHAR(200) NOT NULL,
email VARCHAR(150) UNIQUE NOT NULL,
password_hash VARCHAR(255) NOT NULL, -- Para implementar autenticación [cite: 65]
puntos_totales INT DEFAULT 0,
fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
*/