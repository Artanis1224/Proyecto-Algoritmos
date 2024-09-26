package Interfaz;

import Base.bHistorial;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmHistorial extends javax.swing.JFrame {
    private JPanel panel1;
    private JTable tablaHistorial;
    private JTextField txtBuscar;
    private JButton btnBuscar;

    public frmHistorial() {
        mostrar(""); // Inicializa la tabla con todos los incidentes
        agregarListeners();
    }

    // Muestra los incidentes atendidos
    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            bHistorial func = new bHistorial();
            modelo = func.mostrar(buscar);
            tablaHistorial.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e); // Muestra un mensaje de error en caso de excepción
        }
    }

    // Agregar listeners para las acciones de búsqueda
    private void agregarListeners() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoBuscado = txtBuscar.getText();
                mostrar(tipoBuscado); // Realiza la búsqueda basada en el texto ingresado
            }
        });
    }

    // Muestra la ventana del historial
    public void mostrar() {
        JFrame frame = new JFrame("Historial de Incidentes Atendidos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(520, 620);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frmHistorial historial = new frmHistorial();
        historial.mostrar();
    }
}
