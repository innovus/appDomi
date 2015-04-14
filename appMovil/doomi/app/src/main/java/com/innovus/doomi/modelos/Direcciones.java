package com.innovus.doomi.modelos;

/**
 * Created by personal on 27/03/15.
 */
public class Direcciones {
    private String id;
    private String nombreDireccion;
    private String direccion;
    private String barrio ;
    private String referencia;

    public Direcciones(String id, String nombreDireccion, String direccion, String barrio, String referencia){
        this.nombreDireccion = nombreDireccion;
        this.direccion = direccion;
        this.barrio = barrio;
        this.referencia = referencia;
        this.id = id;

    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
