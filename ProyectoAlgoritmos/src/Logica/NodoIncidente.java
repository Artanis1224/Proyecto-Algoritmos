package Logica;

public class NodoIncidente {
    private int idIncidente;
    private String tipo;
    private String ubicacion;
    private String gravedad;
    private String hora;
    private String estado;
    private NodoIncidente siguiente;
    private NodoIncidente anterior;


    public NodoIncidente(String tipo, String ubicacion, String gravedad, String hora, String estado) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.gravedad = gravedad;
        this.hora = hora;
        this.estado = "pendiente";
        this.siguiente = null;
        this.anterior = null;
    }

    public NodoIncidente() {
    }

    public NodoIncidente(int idIncidente, String tipo, String ubicacion, String gravedad, String hora, String estado) {
    }


    public String getTipo() {
        return tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getGravedad() {
        return gravedad;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public NodoIncidente getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoIncidente siguiente) {
        this.siguiente = siguiente;
    }

    public NodoIncidente getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoIncidente anterior) {
        this.anterior = anterior;
    }

    public int getIdIncidente() {
        return idIncidente;
    }
    public void setIdIncidente(int idIncidente) {
        this.idIncidente = idIncidente;
    }
}
