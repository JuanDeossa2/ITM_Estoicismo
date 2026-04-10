package edu.itm.ejemplo.utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    Connection con;
/* en este caso springboot ya me carga el driver de mysql
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
     */

    public Connection obtenerConexion() {
        try {
            //url = "jdbc:sqlserver://localhost:1433;databaseName=TuBaseDeDatos;user=TuUsuario;password=TuContraseña";
            //con = DriverManager.getConnection(url)
            //jdbc:oracle:thin:@//localhost:1521/xe
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/itm_estoicismo_db", "root", "admin123");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            ex.printStackTrace();
        }
        return con;
    }
    //Probar la base de datos
    public static void main(String[] args) {
        Conexion conection = new Conexion();
        try {
            ResultSet r = conection.obtenerConexion().prepareStatement("select * from usuarios").executeQuery();
            if (r.next()) {
                System.out.println("id: " + r.getString(1) + " nombre: " + r.getString(2));
                while (r.next()) {
                    System.out.println("Id: " + r.getString("id_usuario") + " nombres: " + r.getString("nombre_completo"));
                }
            } else {
                System.out.println("NO HAY DATOS");
            }
        } catch (Exception e) {
            System.out.println("#Excepcion: "+e.getMessage());
        }
    }
}