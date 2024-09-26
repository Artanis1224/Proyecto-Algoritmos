package Base;

import Logica.NodoIncidente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class bIncidente {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    // Inserta un nuevo incidente en la base de datos
    public boolean insertar(NodoIncidente dts) {
        // SQL para insertar un nuevo registro en la tabla incidentes
        sSQL = "INSERT INTO incidentes (tipo, ubicacion, gravedad, hora, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo());
            pst.setString(2, dts.getUbicacion());
            pst.setString(3, dts.getGravedad());
            pst.setString(4, dts.getHora());
            pst.setString(5, dts.getEstado());

            int n = pst.executeUpdate();
            return n != 0; // Retorna true si se insertó al menos un registro

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false; // Retorna false en caso de error
        }
    }
}
