package Interfaz;

import Base.bListaPendientes;
import Logica.NodoIncidente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmIncidentesPendientes extends javax.swing.JFrame {
    private JPanel panel1;
    private JTable tablaPendientes;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;

    public frmIncidentesPendientes() {
        mostrar(""); // Llama a mostrar para inicializar la tabla
        agregarListeners(); // Configura los eventos de los botones
    }

    // Método para mostrar los incidentes en la tabla según un criterio de búsqueda
    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            bListaPendientes func = new bListaPendientes();
            modelo = func.mostrar(buscar); // Carga el modelo de la tabla con incidentes
            tablaPendientes.setModel(modelo);

            // Ocultar la columna ID (asumiendo que es la primera columna)
            tablaPendientes.getColumnModel().getColumn(0).setMinWidth(0);
            tablaPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaPendientes.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    // Método para agregar listeners a los botones
    private void agregarListeners() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoBuscado = txtBuscar.getText();
                mostrar(tipoBuscado); // Llama al método mostrar con el texto de búsqueda
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPendientes.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    int idIncidente = Integer.parseInt(tablaPendientes.getValueAt(filaSeleccionada, 0).toString());
                    int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estás seguro de eliminar el incidente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        NodoIncidente incidenteAEliminar = new NodoIncidente();
                        incidenteAEliminar.setIdIncidente(idIncidente);
                        bListaPendientes func = new bListaPendientes();
                        if (func.eliminar(incidenteAEliminar)) {
                            JOptionPane.showMessageDialog(rootPane, "Incidente eliminado con éxito.");
                            mostrar(""); // Actualizar la tabla
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Error al eliminar el incidente.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Debes seleccionar un incidente para eliminar.");
                }
            }
        });
    }

    // Metodo para mostrar la ventana de incidentes pendientes
    public void mostrar() {
        JFrame frame = new JFrame("Incidentes Pendientes");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiado a DISPOSE_ON_CLOSE
        frame.setSize(520, 620); // Establecer tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        frmIncidentesPendientes formulario = new frmIncidentesPendientes();
        formulario.mostrar(); // Mostrar la ventana
    }
}
