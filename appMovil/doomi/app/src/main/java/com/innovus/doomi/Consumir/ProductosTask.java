package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.appspot.domi_app.domi.Domi;

import com.appspot.domi_app.domi.model.Empresa;
import com.appspot.domi_app.domi.model.Producto;
import com.appspot.domi_app.domi.model.ProductoCollection;
import com.innovus.doomi.R;
import com.innovus.doomi.adapters.EmpresasAdapter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Janeth Arcos on 19/02/2015.
 */
public class ProductosTask extends AsyncTask<String, Void, List<Producto>> {

    // private static com.appspot.domi_app.domi.Domi myApiService = null;
    private com.appspot.domi_app.domi.Domi myApiService = null;
    public String mostrar="";

    private Activity activity;

    public static void build(Context context) {
        //myApiService = buildServiceHandler(context);
    }


    public ProductosTask(Activity activity ) {
        super();
        this.activity = activity;

    }




    @Override
    protected List<Producto> doInBackground(String... strings) {

        if (myApiService == null) { // Only do this once
                myApiService = AppConstants.buildServiceHandler();
            }
            // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

            try {
                Domi.GetProductosXEmpresa queryProductos = myApiService.getProductosXEmpresa(strings[0]);

                ProductoCollection productoCollection = queryProductos.execute();
                if (productoCollection != null && productoCollection.getItems() != null) {
                    List<Producto> productos = productoCollection.getItems();

                    return productos;
                }
                //return Collections.EMPTY_LIST;
                return queryProductos.execute().getItems();
                //return myApiService.getProductosXEmpresa(strings[i]).execute().getItems();

            } catch (IOException e) {
                Log.e("Erroroooooooor", e.getMessage());
                //  Log.e("Error",e);
                return Collections.EMPTY_LIST;
            }

        }

    @Override
    protected void onPostExecute(List<Producto> result) {

        // TextView greetingIdText = (TextView) findViewById(R.id.prueba);
        if(result.size()==0){
            Toast.makeText(activity, "No ahi productos", Toast.LENGTH_LONG).show();

        }else{
            for (Producto q : result) {
                //empresasA.add(q.getNombre());
                mostrar += "- " + q.getNombreProducto()+", "+ q.getCategoriaPropietaria() ;

                //Toast.makeText(context, mostrar, Toast.LENGTH_LONG).show();
            }

            Toast.makeText(activity, mostrar, Toast.LENGTH_LONG).show();


        }

        // greetingIdText.setText(mostrar);



/*
        RecyclerView recyclerView = (RecyclerView)  activity.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new EmpresasAdapter(result, R.layout.row_empresas,activity));
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
*/


    }



}
