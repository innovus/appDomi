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
import com.appspot.domi_app.domi.model.EmpresaCollection;
import com.innovus.doomi.R;
import com.innovus.doomi.adapters.EmpresasAdapter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Janeth Arcos on 19/02/2015.
 */
public class HttpRequestTask extends AsyncTask<Void, Void, List<Empresa>> {
   // private static com.appspot.domi_app.domi.Domi myApiService = null;
   private com.appspot.domi_app.domi.Domi myApiService = null;


    private Activity activity;

    public static void build(Context context) {
        //myApiService = buildServiceHandler(context);
    }

    public HttpRequestTask(Activity activity) {
        super();
        this.activity = activity;
    }





    @Override
    protected List<Empresa> doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            myApiService = AppConstants.buildServiceHandler();
        }
        // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

        try {
            Domi.ConsultaEmpresas queryEmpresas = myApiService.consultaEmpresas();

            EmpresaCollection empresaCollection = queryEmpresas.execute();
            if (empresaCollection != null && empresaCollection.getItems() != null) {
                List<Empresa> empresas = empresaCollection.getItems();

                return empresas;
            }
            //return Collections.EMPTY_LIST;
            return myApiService.consultaEmpresas().execute().getItems();

        } catch (IOException e) {
            Log.e("Erroroooooooor", e.getMessage());
            //  Log.e("Error",e);
            return Collections.EMPTY_LIST;
        }


    }

    @Override
    protected void onPostExecute(List<Empresa> result) {
        // TextView greetingIdText = (TextView) findViewById(R.id.prueba);
      /*  for (Empresa q : result) {
            //empresasA.add(q.getNombre());
            mostrar += " " + q.getNombre() ;

            //Toast.makeText(context, mostrar, Toast.LENGTH_LONG).show();
        }

        Toast.makeText(context, mostrar, Toast.LENGTH_LONG).show();*/

        // greetingIdText.setText(mostrar);




          RecyclerView recyclerView = (RecyclerView)  activity.findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);//que todo lo optimize
             recyclerView.setAdapter(new EmpresasAdapter(result, R.layout.row_empresas,activity));
            recyclerView.setLayoutManager(new LinearLayoutManager (activity));//linear x q es lienas o si no tambn grillas
            recyclerView.setItemAnimator(new DefaultItemAnimator());



    }


}
