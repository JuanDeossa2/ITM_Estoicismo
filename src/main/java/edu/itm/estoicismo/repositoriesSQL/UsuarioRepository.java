package edu.itm.estoicismo.repositoriesSQL;

import edu.itm.estoicismo.entitiesSQL.Usuarios;
import edu.itm.estoicismo.utilities.Conexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class UsuarioRepository {

    @Autowired
    private UsuarioRepositoryHelper helper;

    public List<Usuarios> buscarUsuarios() {
        List<Usuarios> usuario = new ArrayList<>();
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.listarUsuarios());
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuario.add(new Usuarios(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return usuario;
    }

    public int generarNuevoIdUsuario() {
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.obtenerUltimoIdUsuario());
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1) + 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return 1;
    }

    public Usuarios buscarUsuarioPorEmail(String email) {
        Usuarios user = null;
        Conexion con = new Conexion();
        // Query para buscar por el campo email
        String query = "SELECT id_usuario, nombre_completo, email, password_hash, puntos_totales, fecha_registro FROM usuarios WHERE email = ?";

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Usuarios insertarUsuario(Usuarios user) {
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.insertarUsuario())) {

            ps.setString(1, user.getNombreCompleto()); // 1
            ps.setString(2, user.getEmail()); // 2
            ps.setString(3, user.getPasswordHash()); // 3
            ps.setInt(4, user.getPuntosTotales()); // 4

            long timeInMs = user.getFechaRegistro().getTime();
            ps.setDate(5, new java.sql.Date(timeInMs));

            ps.execute();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuarios actualizarUsuario(Usuarios user) {
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.actualizarUsuario())) {
            ps.setString(1, user.getNombreCompleto());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setInt(4, user.getPuntosTotales());
            long timeInMs = user.getFechaRegistro().getTime();
            ps.setDate(5, new java.sql.Date(timeInMs));
            ps.setInt(6, user.getIdUsuario());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            user = null;
        }
        return user;
    }

    public boolean eliminarUsuario(int id) {
        String sqlLecciones = "DELETE FROM progreso_lecciones WHERE id_usuario = ?";
        String sqlRetos = "DELETE FROM registro_retos WHERE id_usuario = ?";
        String sqlPadre = "DELETE FROM usuarios WHERE id_usuario = ?";

        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement psLecciones = c.prepareStatement(sqlLecciones);
             PreparedStatement psRetos = c.prepareStatement(sqlRetos);
             PreparedStatement psPadre = c.prepareStatement(sqlPadre)) {

            psLecciones.setInt(1, id);
            psLecciones.executeUpdate();

            psRetos.setInt(1, id);
            psRetos.executeUpdate();

            psPadre.setInt(1, id);
            int filasAfectadas = psPadre.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuarios BuscarUsuarioId(int id) {
        Usuarios User = null;
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.buscarUsuario())) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User = new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return User;
    }
}
