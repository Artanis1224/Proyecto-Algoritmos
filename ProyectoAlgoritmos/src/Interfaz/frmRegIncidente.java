package Interfaz;

import Logica.ListaIncidentes;
import Logica.NodoIncidente;
import Base.bIncidente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class frmRegIncidente extends javax.swing.JFrame {
    private JPanel panel1;
    private JComboBox<String> cbTipo;
    private JTextField txtUbicacion;
    private JComboBox<String> cbGravedad;
    private JButton btnRegistrar;

    private ListaIncidentes listaIncidentes;
    private bIncidente baseDatos;

    public frmRegIncidente() {
        listaIncidentes = new ListaIncidentes();
        baseDatos = new bIncidente();

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarIncidente(); // Llama al metodo para registrar incidente
            }
        });
    }

    // Metodo para registrar incidente
    private void registrarIncidente() {
        String tipo = (String) cbTipo.getSelectedItem();
        String ubicacion = txtUbicacion.getText();
        String gravedad = (String) cbGravedad.getSelectedItem();

        if (ubicacion.isEmpty()) {
            JOptionPane.showMessageDialog(panel1, "Por favor, complete todos los campos.");
            return;
        }

        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        NodoIncidente nuevoIncidente = new NodoIncidente(tipo, ubicacion, gravedad, hora, "pendiente");
        nuevoIncidente.setEstado("pendiente");
        listaIncidentes.agregarIncidente(tipo, ubicacion, gravedad);

        if (baseDatos.insertar(nuevoIncidente)) {
            JOptionPane.showMessageDialog(panel1, "Incidente registrado exitosamente.");
            // Limpiar el campo de texto de ubicación después del registro
            txtUbicacion.setText("");
        } else {
            JOptionPane.showMessageDialog(panel1, "Error al registrar el incidente en la base de datos.");
        }
    }

    // Metodo para mostrar
    public void mostrar() {
        JFrame frame = new JFrame("Registrar Incidente");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        frmRegIncidente formulario = new frmRegIncidente();
        formulario.mostrar();
    }
}
