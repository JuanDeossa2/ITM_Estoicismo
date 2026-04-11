package edu.itm.ejemplo.repositoriesSQL;

import edu.itm.ejemplo.entitiesSQL.Lecciones;
import edu.itm.ejemplo.utilities.Conexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeccionRepository {

    @Autowired
    private LeccionRepositoryHelper helper;

    public List<Lecciones> listarLecciones() {
        List<Lecciones> lista = new ArrayList<>();
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.listarLecciones());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Lecciones(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int generarNuevoIdLeccion() {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.obtenerUltimoIdLeccion());
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1) + 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public Lecciones insertarLeccion(Lecciones leccion) {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.insertarLeccion())) {

            ps.setInt(1, leccion.getIdLeccion());
            ps.setInt(2, leccion.getIdRuta());
            ps.setString(3, leccion.getTitulo());
            ps.setString(4, leccion.getContenidoTeorico());
            ps.setInt(5, leccion.getOrdenLeccion());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            leccion = null;
        }

        return leccion;
    }

    public Lecciones actualizarLeccion(Lecciones leccion) {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.actualizarLeccion())) {

            ps.setInt(1, leccion.getIdRuta());
            ps.setString(2, leccion.getTitulo());
            ps.setString(3, leccion.getContenidoTeorico());
            ps.setInt(4, leccion.getOrdenLeccion());
            ps.setInt(5, leccion.getIdLeccion());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            leccion = null;
        }

        return leccion;
    }

    public boolean eliminarLeccion(int id) {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.eliminarLeccion())) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Lecciones buscarLeccionPorId(int id) {
        Lecciones leccion = null;
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.buscarLeccionPorId())) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                leccion = new Lecciones(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leccion;
    }
}