package Logica;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ListaIncidentes {
    private NodoIncidente cabeza;

    public ListaIncidentes() {
        cabeza = null; // Inicializa la lista de incidentes como vacía
    }

    // Metodo para agregar un nuevo incidente a la lista
    public void agregarIncidente(String tipo, String ubicacion, String gravedad) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")); // Obtiene la hora actual
        NodoIncidente nuevoNodo = new NodoIncidente(tipo, ubicacion, gravedad, hora, "pendiente");

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoIncidente temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
    }

}
