package Base;

import Logica.NodoIncidente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bListaPendientes {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    // Mostrar incidentes pendientes
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "TIPO", "UBICACIÓN", "GRAVEDAD", "HORA", "ESTADO"};
        String[] registro = new String[6];

        modelo = new DefaultTableModel(null, titulos);

        // Consulta que selecciona solo los incidentes con estado "pendiente" y permite la búsqueda por tipo
        sSQL = "SELECT * FROM incidentes WHERE estado = 'pendiente' AND tipo LIKE '%" + buscar + "%' ORDER BY idIncidente";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idIncidente");
                registro[1] = rs.getString("tipo");
                registro[2] = rs.getString("ubicacion");
                registro[3] = rs.getString("gravedad");
                registro[4] = rs.getString("hora");
                registro[5] = rs.getString("estado");

                modelo.addRow(registro);
            }

            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    // Método para eliminar un incidente
    public boolean eliminar(NodoIncidente dts) {
        sSQL = "DELETE FROM incidentes WHERE idIncidente = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdIncidente());

            int n = pst.executeUpdate();
            return n != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
