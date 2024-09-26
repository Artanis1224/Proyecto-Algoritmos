package Interfaz;

import Logica.NodoVoluntario;
import Logica.ColaPrioridad;
import Base.bVoluntario; // Clase para manejar operaciones de base de datos para voluntarios

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmRegVoluntario extends javax.swing.JFrame {
    private JPanel panel1;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JButton btnRegistro;

    private ColaPrioridad colaVoluntarios;
    private bVoluntario baseDatos;

    public frmRegVoluntario() {
        colaVoluntarios = new ColaPrioridad();
        baseDatos = new bVoluntario();

        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVoluntario(); // Metodo que maneja el registro de un voluntario
            }
        });
    }

    // Metodo para registrar un nuevo voluntario en la cola y en la base de datos
    private void registrarVoluntario() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(panel1, "Por favor, complete todos los campos.");
            return;
        }

        NodoVoluntario nuevoVoluntario = new NodoVoluntario(nombre, apellido);
        colaVoluntarios.encolar(nuevoVoluntario);

        if (baseDatos.insertar(nuevoVoluntario)) {
            JOptionPane.showMessageDialog(panel1, "Voluntario registrado exitosamente.");
            txtNombre.setText("");
            txtApellido.setText("");
        } else {
            JOptionPane.showMessageDialog(panel1, "Error al registrar el voluntario en la base de datos.");
        }
    }

    // Metodo para mostrar la ventana del formulario de registro
    public void mostrar() {
        JFrame frame = new JFrame("Registrar Voluntario");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Metodo principal que inicia la aplicacion
    public static void main(String[] args) {
        frmRegVoluntario formulario = new frmRegVoluntario();
        formulario.mostrar();
    }
}
