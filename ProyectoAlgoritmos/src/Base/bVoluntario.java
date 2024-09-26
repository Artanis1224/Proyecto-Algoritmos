package Base;

import Logica.NodoIncidente;
import Logica.NodoVoluntario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class bVoluntario {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    public boolean insertar (NodoVoluntario dts){
        // SQL para insertar un nuevo registro en la tabla voluntarios
        sSQL = "INSERT INTO voluntarios (nombre, apellido, estado) VALUES(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getEstado());

            int n = pst.executeUpdate();

            return n != 0; // Retorna true si se insertó al menos un registro

        } catch (Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

}
