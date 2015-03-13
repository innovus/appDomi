package com.innovus.doomi.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by personal on 6/03/15.
 */
public class DbProductos extends SQLiteOpenHelper  {

    //llamamos al constructor
    public DbProductos(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    //creamos la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table productos(websafeKey text primary key, nombreProducto text, descripcionProducto text, precioProducto text, cantidad integer, observacion text)");
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists productos");
        db.execSQL("create table productos(websafeKey text primary key, nombreProducto text, descripcionProducto text, precioProducto text, cantidad integer, observacion text)");
    }
}