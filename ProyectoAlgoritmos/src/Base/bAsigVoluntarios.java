package Base;

import Logica.NodoVoluntario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bAsigVoluntarios {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    // Mostrar voluntarios disponibles
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "NOMBRE", "APELLIDO", "ESTADO"};
        String[] registro = new String[4];

        modelo = new DefaultTableModel(null, titulos);

        // Consulta que selecciona solo los voluntarios con estado "disponible" y permite la búsqueda por nombre
        sSQL = "SELECT * FROM voluntarios WHERE estado = 'disponible' AND nombre LIKE '%" + buscar + "%' ORDER BY idVoluntario";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idVoluntario");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("estado");

                modelo.addRow(registro);
            }

            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean actualizarEstadoVoluntario(int idVoluntario, String nuevoEstado) {
        String sSQL = "UPDATE voluntarios SET estado = ? WHERE idVoluntario = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idVoluntario);

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    // Método para actualizar el estado de un incidente
    public boolean actualizarEstadoIncidente(int idIncidente, String nuevoEstado) {
        String sSQL = "UPDATE incidentes SET estado = ? WHERE idIncidente = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idIncidente);

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
