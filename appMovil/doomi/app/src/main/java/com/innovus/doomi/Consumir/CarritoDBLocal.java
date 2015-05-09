package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.innovus.doomi.R;
import com.innovus.doomi.adapters.CarritoAdapter;
import com.innovus.doomi.adapters.EmpresasAdapter;
import com.innovus.doomi.db.DbProductos;
import com.innovus.doomi.modelos.ProductoDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by personal on 10/03/15.
 */
public class CarritoDBLocal   extends AsyncTask<Void, Void, ArrayList<ProductoDB>> {
    private Activity activity;

    public static void build(Context context) {
        //myApiService = buildServiceHandler(context);
    }

    public CarritoDBLocal(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected ArrayList<ProductoDB> doInBackground(Void... params) {
        ArrayList<ProductoDB> ListadoProductosDB = new ArrayList<ProductoDB>();


        DbProductos admin = new DbProductos (activity,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        //String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select websafeKey, nombreProducto , descripcionProducto , precioProducto, cantidad , observacion from productos ", null);

        //recorre la base de datos
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                ProductoDB auxProductoDB = new ProductoDB(fila.getString(0),fila.getString(1),fila.getString(2),fila.getInt(3),fila.getInt(4),fila.getString(5));

                ListadoProductosDB.add(auxProductoDB);
                //total = total + (fila.getInt(0) * fila.getInt(1));
            } while(fila.moveToNext());


        }
        bd.close();
        return ListadoProductosDB;


    }

    @Override
    protected void onPostExecute(ArrayList<ProductoDB> result) {

        RecyclerView recyclerView = (RecyclerView)  activity.findViewById(R.id.my_recycler_carrito);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new CarritoAdapter(result, R.layout.row_pedido_carrito,activity));
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
