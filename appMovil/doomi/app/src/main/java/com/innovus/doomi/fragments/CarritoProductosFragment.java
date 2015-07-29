package com.innovus.doomi.fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.innovus.doomi.Consumir.CarritoDBLocal;
import com.innovus.doomi.Consumir.HttpRequestTask;
import com.innovus.doomi.R;
import com.innovus.doomi.adapters.CarritoAdapter;
import com.innovus.doomi.adapters.EmpresasAdapter;
import com.innovus.doomi.db.DbProductos;
import com.innovus.doomi.modelos.ProductoDB;

import java.util.ArrayList;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class CarritoProductosFragment extends Fragment {

    public CarritoProductosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        this.ActualizarFragemento();


    }
    public ArrayList<ProductoDB>llenarCarrito(){
        ArrayList<ProductoDB> ListadoProductosDB = new ArrayList<ProductoDB>();


        DbProductos admin = new DbProductos (getActivity(),"administracion", null, 1);
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

        } else
            Toast.makeText(getActivity(), "No has pedido nada",
                    Toast.LENGTH_SHORT).show();
        bd.close();
        return ListadoProductosDB;
    }

    @Override
    public void onResume(){
        super.onResume();

        this.ActualizarFragemento();
        //Nuestro código a ejecutar en este momento
    }
    public void ActualizarFragemento(){
        ArrayList <ProductoDB> pusheenArrayList = new ArrayList<ProductoDB>();

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_pedidos);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new CarritoAdapter(this.llenarCarrito(),R.layout.row_pedido_carrito,getActivity(),5000,2000));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());

      //  new CarritoDBLocal(getActivity()).execute();

    }
    public void ActualizarFragementoBusqueda(){
        ArrayList <ProductoDB> pusheenArrayList = new ArrayList<ProductoDB>();

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_pedidos);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new CarritoAdapter(this.llenarCarrito(),R.layout.row_pedido_carrito,getActivity(),5000,2000));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());


      //  new CarritoDBLocal(getActivity()).execute();

    }

}
