package models;

/**
 * Created by Janeth Arcos on 16/02/2015.
 */
public class Categoria {
    private long id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String nombre;

    public Categoria() {
    }
}
