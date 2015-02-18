package fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.innovus.spofity.R;

import org.json.JSONArray;

import java.util.ArrayList;

import adapters.AdapterEmpresas;
import models.Empresa;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmpresasFragment extends Fragment {


    public EmpresasFragment() {
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

        //creo el arraylist y lleno el vector
        ArrayList<Empresa> empresaArrayList = new ArrayList<Empresa>();

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

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new AdapterEmpresas(empresaArrayList,R.layout.row_empresas));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



}
