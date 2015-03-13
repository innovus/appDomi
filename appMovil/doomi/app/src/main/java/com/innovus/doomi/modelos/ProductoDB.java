package com.innovus.doomi.modelos;

/**
 * Created by personal on 9/03/15.
 */
public class ProductoDB {
    private String webSafeKey;
    private int precio ;
    private String descripcion;
    private String nombre ;
    private int cantidad;
    private String observacion;

    public ProductoDB(String webSafeKey, String nombre, String descripcion,int precio,   int cantidad, String observacion){
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.observacion = observacion;
        this.webSafeKey = webSafeKey;
        this.precio = precio;

    }


    public String getWebSafeKey() {
        return webSafeKey;
    }

    public void setWebSafeKey(String webSafeKey) {
        this.webSafeKey = webSafeKey;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public int getTotalPedido(){
        return cantidad*precio;
    }
}
