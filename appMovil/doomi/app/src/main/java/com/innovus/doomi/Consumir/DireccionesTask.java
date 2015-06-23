package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.innovus.doomi.R;
import com.innovus.doomi.adapters.DireccionesAdapter;
import com.innovus.doomi.db.DbDirecciones;

import com.innovus.doomi.modelos.Direcciones;


import java.util.ArrayList;

/**
 * Created by personal on 27/03/15.
 */
public class DireccionesTask extends AsyncTask<Void, Void, ArrayList<Direcciones>> {
    private Activity activity;
    private boolean b;

    public static void build(Context context) {
        //myApiService = buildServiceHandler(context);
    }

    public DireccionesTask(Activity activity,boolean b) {
        super();
        this.activity = activity;
        this.b = b;
    }

    @Override
    protected ArrayList<Direcciones> doInBackground(Void... params) {
        ArrayList<Direcciones> ListadoDirecciones = new ArrayList<Direcciones>();


        DbDirecciones admin = new DbDirecciones(activity, "administracionn", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        //String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select id, nombreDireccion , direccion , barrio, referencia  from direcciones ", null);

        //recorre la base de datos
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Direcciones auxDirecciones = new Direcciones(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4));

                ListadoDirecciones.add(auxDirecciones);
                //total = total + (fila.getInt(0) * fila.getInt(1));
            } while(fila.moveToNext());


        }
        bd.close();
        return ListadoDirecciones;


    }

    @Override
    protected void onPostExecute(ArrayList<Direcciones> result) {

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view_direcciones);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new DireccionesAdapter(result, R.layout.row_direcciones, activity,b));
        recyclerView.setLayoutManager(new GridLayoutManager(activity,2));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
