package com.innovus.doomi.fragments;

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

import com.innovus.doomi.Consumir.HttpRequestTask;

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
    public static String mostrar="";

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
        ArrayList <Empresa> pusheenArrayList = new ArrayList<Empresa>();
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new EmpresasAdapter(pusheenArrayList,R.layout.row_empresas,getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
