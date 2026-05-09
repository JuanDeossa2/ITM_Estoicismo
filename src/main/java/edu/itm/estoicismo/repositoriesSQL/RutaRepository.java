package edu.itm.estoicismo.repositoriesSQL;

import edu.itm.estoicismo.entitiesSQL.RutaAprendizaje;
import edu.itm.estoicismo.utilities.Conexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RutaRepository {
    @Autowired private RutaRepositoryHelper helper;

    public List<RutaAprendizaje> BuscarRutas() {
        List<RutaAprendizaje> lista = new ArrayList<>();
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.listarRutas());
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new RutaAprendizaje(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public int generarNuevoId() {
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.obtenerUltimoId());
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1) + 1;
        } catch (SQLException e) { e.printStackTrace(); }
        return 1;
    }

    public RutaAprendizaje insertar(RutaAprendizaje ruta) {
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.insertarRuta())) {
            ps.setInt(1, ruta.getIdRuta());
            ps.setString(2, ruta.getNombreRuta());
            ps.setString(3, ruta.getDescripcion());
            ps.setString(4, ruta.getNivelDificultad());
            ps.execute();
        } catch (SQLException e) { e.printStackTrace(); ruta = null; }
        return ruta;
    }

    public RutaAprendizaje actualizar(RutaAprendizaje ruta) {
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.actualizarRuta())) {

            ps.setString(1, ruta.getNombreRuta());
            ps.setString(2, ruta.getDescripcion());
            ps.setString(3, ruta.getNivelDificultad());
            ps.setInt(4, ruta.getIdRuta());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            ruta = null;
        }
        return ruta;
    }

    public boolean eliminar(int idRuta) {
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.eliminarRuta())) {

            ps.setInt(1, idRuta);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public RutaAprendizaje BuscarRutaId(int id) {
        RutaAprendizaje ruta = null;
        Conexion con = new Conexion();
        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.buscarRuta())) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ruta = new RutaAprendizaje(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruta;
    }
}