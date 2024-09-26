package Interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuPrincipal {
    private JPanel panel1;
    private JMenuBar menuBar;
    private JMenu menuInicio;
    private JMenu menuRegistrarIncidente;
    private JMenu menuIncidentesPendientes;
    private JMenu menuRecursos;
    private JMenu menuHistorial;
    private JMenu menuSalir;
    private JMenuItem menuRegistrarVoluntario;
    private JMenuItem menuAsignar;

    public menuPrincipal() {
        inicializarAcciones(); // Inicializa las acciones para cada menú y botón
    }

    // Metodo para inicializar las acciones de los menús y botones
    private void inicializarAcciones() {
        menuRegistrarVoluntario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRegVoluntario registroVoluntario = new frmRegVoluntario();
                registroVoluntario.mostrar(); // Abre la ventana para registrar voluntario
            }
        });

        menuAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmAsignarVoluntario asignarVoluntario = new frmAsignarVoluntario();
                asignarVoluntario.mostrar(); // Abre la ventana para asignar voluntario
            }
        });

        JMenuItem itemRegistrarIncidente = new JMenuItem("Registrar Incidente");
        itemRegistrarIncidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRegIncidente registroIncidente = new frmRegIncidente();
                registroIncidente.mostrar(); // Abre la ventana para registrar un incidente
            }
        });
        menuRegistrarIncidente.add(itemRegistrarIncidente);

        JMenuItem itemIncidentesPendientes = new JMenuItem("Ver Incidentes Pendientes");
        itemIncidentesPendientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmIncidentesPendientes incidentesPendientes = new frmIncidentesPendientes();
                incidentesPendientes.mostrar(); // Abre la ventana para ver incidentes pendientes
            }
        });
        menuIncidentesPendientes.add(itemIncidentesPendientes);

        JMenuItem itemHistorial = new JMenuItem("Ver Historial");
        itemHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmHistorial historial = new frmHistorial();
                historial.mostrar(); // Abre la ventana para ver historial
            }
        });
        menuHistorial.add(itemHistorial);

        JMenuItem menuSalirApp = new JMenuItem("Salir");
        menuSalirApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cierra la aplicación
            }
        });
        menuSalir.add(menuSalirApp);
    }

    // Metodo principal para crear y mostrar la ventana principal
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Gestión de emergencias Urbanas");
        frame.setContentPane(new menuPrincipal().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 800);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
    }
}
