package Interfaz;

import Base.bAsigVoluntarios;
import Logica.ColaPrioridad;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmAsignarVoluntario extends javax.swing.JFrame {
    private JPanel panel1;
    private JTable tablaVoluntariosDisp;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnAsignar;

    private bAsigVoluntarios baseDatosVoluntarios;
    private ColaPrioridad colaPrioridad;

    public frmAsignarVoluntario() {
        baseDatosVoluntarios = new bAsigVoluntarios();
        colaPrioridad = new ColaPrioridad();

        mostrarVoluntarios("");

        // Acción del botón para asignar voluntario
        btnAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarVoluntario();
            }
        });

        // Acción del botón de búsqueda
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBuscado = txtBuscar.getText();
                mostrarVoluntarios(nombreBuscado);
            }
        });
    }

    // Muestra la lista de voluntarios disponibles, opcionalmente filtrando por un texto de búsqueda
    private void mostrarVoluntarios(String buscar) {
        DefaultTableModel modelo = baseDatosVoluntarios.mostrar(buscar); // Obtiene el modelo de la tabla de voluntarios filtrado por el nombre
        tablaVoluntariosDisp.setModel(modelo);

        // Ocultar la columna ID (asumiendo que es la primera columna)
        tablaVoluntariosDisp.getColumnModel().getColumn(0).setMinWidth(0);
        tablaVoluntariosDisp.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaVoluntariosDisp.getColumnModel().getColumn(0).setWidth(0);
    }

    // Asigna el voluntario seleccionado al incidente, verificando la selección
    private void asignarVoluntario() {
        int filaSeleccionada = tablaVoluntariosDisp.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(panel1, "Por favor, selecciona un voluntario.");
            return;
        }

        int idVoluntario = Integer.parseInt(tablaVoluntariosDisp.getValueAt(filaSeleccionada, 0).toString()); // Obtiene el ID del voluntario seleccionado

        // Llama al metodo en ColaPrioridad para asignar el voluntario
        if (colaPrioridad.asignarVoluntario(idVoluntario)) {
            JOptionPane.showMessageDialog(panel1, "Voluntario asignado al incidente.");
        } else {
            JOptionPane.showMessageDialog(panel1, "No hay incidentes pendientes para asignar o error al asignar.");
        }
        mostrarVoluntarios(""); // Actualiza la tabla de voluntarios
    }

    // Muestra la ventana para asignar voluntarios
    public void mostrar() {
        JFrame frame = new JFrame("Asignar Voluntario");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack(); // Ajusta el tamaño al contenido
        frame.setLocationRelativeTo(null);
        frame.setSize(520, 620);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frmAsignarVoluntario formulario = new frmAsignarVoluntario();
        formulario.mostrar();
    }
}
