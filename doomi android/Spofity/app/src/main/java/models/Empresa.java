package models;

/**
 * Created by Janeth Arcos on 16/02/2015.
 */
public class Empresa {
    private String Nombre;
    private String Descripcion;
    private int TiempoMinimo;
    private int Pedido;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getTiempoMinimo() {
        return TiempoMinimo;
    }

    public void setTiempoMinimo(int tiempoMinimo) {
        TiempoMinimo = tiempoMinimo;
    }

    public int getPedido() {
        return Pedido;
    }

    public void setPedido(int pedido) {
        Pedido = pedido;
    }


}
