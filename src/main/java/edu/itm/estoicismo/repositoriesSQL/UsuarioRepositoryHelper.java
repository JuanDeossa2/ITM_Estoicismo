package edu.itm.estoicismo.repositoriesSQL;

import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryHelper {
    // Buscar todos los usuarios
    public String listarUsuarios() { return "SELECT id_usuario, nombre_completo, email, password_hash, puntos_totales, fecha_registro FROM usuarios"; }
    // Buscar todos los usuario por id_usuario
    public String buscarUsuario(){ return "SELECT id_usuario, nombre_completo, email, password_hash, puntos_totales, fecha_registro FROM usuarios WHERE id_usuario = ?";}
    // Crear Usuario
    public String obtenerUltimoIdUsuario() { return "SELECT MAX(id_usuario) FROM usuarios"; }
    public String insertarUsuario() { return "INSERT INTO usuarios (nombre_completo, email, password_hash, puntos_totales, fecha_registro) VALUES (?, ?, ?, ?, ?)";}    // Actualizar Usuario
    public String actualizarUsuario() { return "UPDATE usuarios SET nombre_completo = ?, email = ?, password_hash = ?, puntos_totales = ?, fecha_registro = ? WHERE id_usuario = ?"; }
    // Eliminar Usuario
    public String eliminarUsuario() { return "DELETE FROM usuarios WHERE id_usuario = ?"; }
}
