package fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovus.spofity.R;

import java.util.ArrayList;

import adapters.AdapterCategorias;
import models.Categoria;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment {


    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categorias, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //creo el arraylist y lleno el vector
        ArrayList<Categoria> categoriasArrayList = new ArrayList<Categoria>();

        Categoria restaurante = new Categoria();
        restaurante.setNombre("Restaurantes");
        restaurante.setId(1);

        categoriasArrayList.add(restaurante);


        Categoria ropa = new Categoria();
        ropa.setNombre("Ropa");
        ropa.setId(2);

        categoriasArrayList.add(ropa);


        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_cat);
        recyclerView.setHasFixedSize(true);//que todo lo optimize
        recyclerView.setAdapter(new AdapterCategorias(categoriasArrayList,R.layout.row_categorias));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//linear x q es lienas o si no tambn grillas
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}
