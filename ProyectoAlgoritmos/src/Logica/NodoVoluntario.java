package Logica;

public class NodoVoluntario {
    private int idVoluntario;
    private String nombre;
    private String apellido;
    private String estado;
    private NodoVoluntario siguiente;

    public NodoVoluntario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = "disponible";
        this.siguiente = null;
    }

    public NodoVoluntario(int idVoluntario, String nombre, String apellido, String estado) {
    }

    public NodoVoluntario() {

    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public NodoVoluntario getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoVoluntario siguiente) {
        this.siguiente = siguiente;
    }

    public int getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdvoluntario(String idvoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Voluntario: " + nombre + " " + apellido + ", Estado: " + estado;
    }
}
