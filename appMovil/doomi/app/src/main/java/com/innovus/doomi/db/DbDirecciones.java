package com.innovus.doomi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by personal on 27/03/15.
 */
public class DbDirecciones  extends SQLiteOpenHelper {

    //llamamos al constructor
    public DbDirecciones(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    //creamos la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table direcciones(id integer primary key AUTOINCREMENT NOT NULL, nombreDireccion text, direccion text, barrio text, referencia text)");
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists direcciones");
        db.execSQL("create table direcciones(id integer primary key AUTOINCREMENT NOT NULL, nombreDireccion text, direccion text, barrio text, referencia text)");
    }
}
