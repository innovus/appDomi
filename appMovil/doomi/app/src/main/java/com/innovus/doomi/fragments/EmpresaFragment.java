package com.innovus.doomi.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appspot.domi_app.domi.Domi;
import com.appspot.domi_app.domi.model.EmpresaCollection;
import com.innovus.doomi.Consumir.AppConstants;
import com.innovus.doomi.R;
import com.appspot.domi_app.domi.model.Empresa;
import com.innovus.doomi.adapters.EmpresasAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class EmpresaFragment extends Fragment {

    public EmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empresas, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        new HttpRequestTask().execute();

        /*
        String URL = "https://domi-app.appspot.com/_ah/api/domi/v1/consultaEmpresas";
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Espere por Favor","Estamos atendiendo su solicitud");//el


        JsonArrayRequest req = new JsonArrayRequest(URL,new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //cuando sea exitoso
                Log.e("mi respuesta", response.toString());

                progressDialog.cancel();//cuando sea extisoso ocultate
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());

                progressDialog.cancel();//cuando exista error no lo muestres

            }
        });


        queue.add(req);//agregar una peticion
*/

        //creo el arraylist y lleno el vector
        /*ArrayList<Empresa> empresaArrayList = new ArrayList<Empresa>();

        Empresa mister = new Empresa();
        mister.setDescripcion("Mejor pollo a la broester de pasto");
        mister.setNombre("Mister Pollo");
        mister.setPedido(10000);
        mister.setTiempoMinimo(12);
        empresaArrayList.add(mister);


        Empresa janethArcos = new Empresa();
        janethArcos.setDescripcion("ALquiler y Venta de Vestidos");
        janethArcos.setNombre("Janeth Arcos Diseñadora de Modas");
        janethArcos.setPedido(80000);
        janethArcos.setTiempoMinimo(60);



        empresaArrayList.add(janethArcos);
        */


    }



    public static Domi buildServiceHandler() {

        Domi.Builder builder
                = new Domi.Builder(
                AppConstants.HTTP_TRANSPORT,
                AppConstants.JSON_FACTORY, null);

        //builder.setApplicationName("domi-app");
        return builder.build();
    }



    private class HttpRequestTask extends AsyncTask<Void, Void, List<Empresa>> {


        private com.appspot.domi_app.domi.Domi myApiService = null;

        @Override
        protected List<Empresa> doInBackground(Void... params) {
            if (myApiService == null) { // Only do this once
                myApiService = buildServiceHandler();
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
                return Collections.EMPTY_LIST;
            }


        }

        @Override
        protected void onPostExecute(List<Empresa> result) {
            // TextView greetingIdText = (TextView) findViewById(R.id.prueba);



            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);//que todo lo optimize
             recyclerView.setAdapter(new EmpresasAdapter(result,R.layout.row_empresas));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }

    }

}
