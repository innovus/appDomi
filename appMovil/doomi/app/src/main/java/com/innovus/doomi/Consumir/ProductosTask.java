package com.innovus.doomi.Consumir;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.appspot.domi_app.doomiTodos.DoomiTodos;
import com.appspot.domi_app.doomiTodos.model.Producto;
import com.appspot.domi_app.doomiTodos.model.ProductoCollection;
import com.appspot.domi_app.doomiUsuarios.DoomiUsuarios;
import com.innovus.doomi.R;
import com.innovus.doomi.adapters.EmpresasAdapter;
import com.innovus.doomi.adapters.ExpandibleCategoriasAdapter;
import com.innovus.doomi.modelos.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Janeth Arcos on 19/02/2015.
 */
public class ProductosTask extends AsyncTask<String, Void, List<Producto>> {


    // private static com.appspot.domi_app.domi.Domi myApiService = null;
    private com.appspot.domi_app.doomiTodos.DoomiTodos myApiService = null;


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
                myApiService = AppConstants.buildServiceHandlerTodos();
            }
            // Domi.ConsultaEmpresa queryEmpresas = myApiService.consultaEmpresa();

            try {

                DoomiTodos.GetProductosXSucursal queryProductos = myApiService.getProductosXSucursal(strings[0]);


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
    protected void onPostExecute(List<Producto> result)  {
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<Producto> arrayChildren = new ArrayList<Producto>();

       /* for (Producto q : result) {
            Parent parent = new Parent();
            parent.setTitle(q.getCategoriaPropietaria());
            arrayChildren = new ArrayList<Producto>();
            arrayChildren.add(q);
            parent.setArrayChildren(arrayChildren);
            arrayParents.add(parent);
        }
        */
        // TextView greetingIdText = (TextView) findViewById(R.id.prueba);

        for (Producto q : result) {
            Boolean repetido = false;
            int  posicion = 0;
            for(int j = 0;j< arrayParents.size(); j++){
                String titulo = arrayParents.get(j).getTitle();
                String categoria = q.getCategoriaPropietaria();
                if(titulo.equals(categoria)){
                    repetido = true;
                    posicion = j;
                    j=(arrayParents.size()) + 1;
                }
            }
            if(repetido == true){
                arrayChildren.add(q);
                arrayParents.get(posicion).setArrayChildren(arrayChildren);
            }else{
                Parent parent = new Parent();
                parent.setTitle(q.getCategoriaPropietaria());
                arrayChildren = new ArrayList<Producto>();
                arrayChildren.add(q);
                parent.setArrayChildren(arrayChildren);
                arrayParents.add (parent);
            }

        }
        ExpandableListView mExpandableList = (ExpandableListView)activity.findViewById(R.id.expandableListView);
        final ExpandibleCategoriasAdapter mAdaptador= new ExpandibleCategoriasAdapter (activity,arrayParents,R.layout.exp_lista_categoria,R.layout.exp_lista_producto);
        mExpandableList.setAdapter(mAdaptador);
       // mExpandableList.setOnChildClickListener(this);

        //mExpandableList.setAdapter(new ExpandibleCategoriasAdapter(activity,arrayParents));

    }
   /* @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
        Producto alumno = (Producto)new ExpandibleCategoriasAdapter(activity,arrayParents, R.layout.exp_lista_categoria,R.layout.exp_lista_producto).getChild(groupPosition, childPosition);
        Toast.makeText(activity, alumno.getNombreProducto() + " - ", Toast.LENGTH_SHORT).show();
        // Se retorna true para indicar que el evento ya ha sido gestionado.
        return true;
    }*/



}
