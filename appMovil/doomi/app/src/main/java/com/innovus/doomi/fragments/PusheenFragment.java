package com.innovus.doomi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovus.doomi.Pusheen;
import com.innovus.doomi.R;
import com.innovus.doomi.adapters.PusheenAdapter;

import java.util.ArrayList;


/**
 * Created by Janeth Arcos on 18/02/2015.
 */
public class PusheenFragment extends Fragment {


    public PusheenFragment() {
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



        //creo el arraylist y lleno el vector
        ArrayList <Pusheen> pusheenArrayList = new ArrayList<Pusheen>();

        Pusheen mister = new Pusheen();
        mister.setPasTime("Mejor pollo a la broester de pasto");
        mister.setName("Mister Pollo");
        mister.setId(1);

        pusheenArrayList.add(mister);
        Pusheen janethArcos = new Pusheen();
        janethArcos.setPasTime("ALquiler y Venta de Vestidos");
        janethArcos.setName("Janeth Arcos Diseñadora de Modas");
        janethArcos.setId(2);

        pusheenArrayList.add(mister);

        pusheenArrayList.add(janethArcos);
        pusheenArrayList.add(janethArcos);
        pusheenArrayList.add(janethArcos);
        pusheenArrayList.add(janethArcos);
        pusheenArrayList.add(janethArcos);
        pusheenArrayList.add(janethArcos);
        pusheenArrayList.add(janethArcos);
        pusheenArrayList.add(janethArcos);


        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new PusheenAdapter(pusheenArrayList,R.layout.row_empresas));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}
