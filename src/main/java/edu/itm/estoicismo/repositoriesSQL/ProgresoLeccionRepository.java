package edu.itm.estoicismo.repositoriesSQL;

import edu.itm.estoicismo.entitiesSQL.ProgresoLecciones;
import edu.itm.estoicismo.utilities.Conexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProgresoLeccionRepository {

    @Autowired
    private ProgresoLeccionRepositoryHelper helper;

    public List<ProgresoLecciones> listarProgreso() {
        List<ProgresoLecciones> lista = new ArrayList<>();
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.listarProgreso());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new ProgresoLecciones(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int generarNuevoId() {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.obtenerUltimoIdProgreso());
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1) + 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public ProgresoLecciones insertar(ProgresoLecciones progreso) {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.insertarProgreso())) {

            ps.setInt(1, progreso.getIdProgreso());
            ps.setInt(2, progreso.getIdUsuario());
            ps.setInt(3, progreso.getIdLeccion());
            ps.setTimestamp(4, progreso.getCompletadaEn());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            progreso = null;
        }

        return progreso;
    }

    public boolean eliminar(int idProgreso) {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.eliminarProgreso())) {

            ps.setInt(1, idProgreso);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ProgresoLecciones buscarPorId(int id) {
        ProgresoLecciones progreso = null;
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.buscarPorIdProgreso())) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                progreso = new ProgresoLecciones(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return progreso;
    }

    public ProgresoLecciones actualizar(ProgresoLecciones progreso) {
        Conexion con = new Conexion();

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(helper.actualizar())) {

            ps.setInt(1, progreso.getIdUsuario());
            ps.setInt(2, progreso.getIdLeccion());
            ps.setTimestamp(3, progreso.getCompletadaEn());
            ps.setInt(4, progreso.getIdProgreso());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            progreso = null;
        }

        return progreso;
    }
}