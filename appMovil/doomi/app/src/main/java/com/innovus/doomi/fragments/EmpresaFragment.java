package com.innovus.doomi.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appspot.domi_app.doomiUsuarios.model.Sucursal;
import com.innovus.doomi.Consumir.HttpRequestTask;

import com.innovus.doomi.R;

import com.innovus.doomi.adapters.EmpresasAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class EmpresaFragment extends Fragment {
    public static String mostrar="";
    private String busqueda="";
    EmpresasAdapter empresaAdapter;
    RecyclerView recyclerView;


    public EmpresaFragment() {
        // Required empty public constructor
    }

    //comunitacion cn el otro fragmento al hacer on...
    public interface OnBusquedaListener {
        public void onBusqueda(String cadena);
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
        ArrayList <Sucursal> pusheenArrayList = new ArrayList<Sucursal>();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        //crea adaptador
        empresaAdapter = new EmpresasAdapter(pusheenArrayList,R.layout.row_empresas,getActivity());
        recyclerView.setAdapter(empresaAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    //intereactuar cn el otro fragmento
    OnBusquedaListener mListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBusquedaListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    public void ponerBusqueda(String busqueda){
        this.busqueda = busqueda;
        ((EmpresasAdapter) recyclerView.getAdapter()).setFilter(busqueda);
        //Toast.makeText(getActivity(), busqueda, Toast.LENGTH_LONG).show();
    }

}
