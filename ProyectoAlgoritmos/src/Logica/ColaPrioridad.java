package Logica;

import Base.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ColaPrioridad {
    private NodoVoluntario inicio;
    private NodoVoluntario fin;
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    public ColaPrioridad() {
        inicio = null;
        fin = null;
    }

    // Metodo para obtener el incidente de mayor gravedad
    public NodoIncidente obtenerIncidenteMayorGravedad() {
        NodoIncidente incidente = null;

        sSQL = "SELECT * FROM incidentes WHERE estado = 'pendiente' ORDER BY " +
                "CASE gravedad WHEN 'Grave' THEN 3 WHEN 'Serio' THEN 2 WHEN 'Leve' THEN 1 END DESC LIMIT 1";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            if (rs.next()) {
                incidente = new NodoIncidente(
                        rs.getInt("idIncidente"),
                        rs.getString("tipo"),
                        rs.getString("ubicacion"),
                        rs.getString("gravedad"),
                        rs.getString("hora"),
                        rs.getString("estado")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return incidente;
    }

    // Metodo actualizado para asignar un voluntario a un incidente
    public boolean asignarVoluntario(int idVoluntario) {
        NodoIncidente incidenteGrave = obtenerIncidenteMayorGravedad();

        if (incidenteGrave != null) {
            try {
                // Actualizar el estado del incidente a 'atendido'
                if (actualizarEstadoIncidente(incidenteGrave.getIdIncidente(), "atendido")) {
                    // Actualizar el estado del voluntario a 'ocupado'
                    actualizarEstadoVoluntario(idVoluntario, "ocupado");
                    return true; // Retornar verdadero si la asignacion fue exitosa
                } else {
                    return false; // Retornar falso si no se pudo actualizar el estado del incidente
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false; // Retornar falso en caso de error
            }
        }
        return false; // Retornar falso si no hay incidentes pendientes
    }

    // Metodo para actualizar el estado de un incidente
    private boolean actualizarEstadoIncidente(int idIncidente, String nuevoEstado) {
        try {
            sSQL = "UPDATE incidentes SET estado = ? WHERE idIncidente = ?";
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idIncidente);
            return pst.executeUpdate() > 0; // Retorna verdadero si se actualiza correctamente
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retornar falso en caso de error
        }
    }

    // Metodo para actualizar el estado de un voluntario
    private boolean actualizarEstadoVoluntario(int idVoluntario, String nuevoEstado) {
        try {
            sSQL = "UPDATE voluntarios SET estado = ? WHERE idVoluntario = ?";
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idVoluntario);
            return pst.executeUpdate() > 0; // Retorna verdadero si se actualiza correctamente
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retornar falso en caso de error
        }
    }

    // Metodo para encolar un nuevo voluntario
    public void encolar(NodoVoluntario nuevoVoluntario) {
        if (inicio == null) {
            inicio = nuevoVoluntario;
            fin = nuevoVoluntario;
        } else {
            fin.setSiguiente(nuevoVoluntario);
            fin = nuevoVoluntario;
        }
    }
}
