package Base;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class bHistorial {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    // Muestra los incidentes atendidos
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"N°", "TIPO", "UBICACIÓN", "GRAVEDAD", "HORA", "ESTADO"};
        String[] registro = new String[6];

        modelo = new DefaultTableModel(null, titulos);

        // Consulta que selecciona solo los incidentes con estado "atendido" y permite la búsqueda por tipo
        sSQL = "SELECT * FROM incidentes WHERE estado = 'atendido' AND tipo LIKE '%" + buscar + "%' ORDER BY idIncidente";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            int numeroFila = 1; // Inicializa la numeración de filas

            while (rs.next()) {
                registro[0] = String.valueOf(numeroFila++); // Asigna el número de fila
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
}
