package com.innovus.doomi.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovus.doomi.Activities.AddDirecciones;
import com.innovus.doomi.modelos.Direcciones;

import com.innovus.doomi.R;


import java.util.List;

/**
 * Created by personal on 27/03/15.
 */
public class DireccionesAdapter extends RecyclerView.Adapter<DireccionesAdapter.ViewHolder> {
    public static Activity activity;

    private List<Direcciones> direcciones;
    private int itemLayout;
    private static boolean is_select;

    public DireccionesAdapter(List<Direcciones> data,int itemLayout,Activity activity,boolean is_select){
        super();
        this.itemLayout = itemLayout;
        this.direcciones = data;
        this.activity = activity;
        this.is_select = is_select;

    }

    @Override
    public DireccionesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(DireccionesAdapter.ViewHolder holder, int position) {

        //lcargo el view con los valores
        Direcciones direccion = direcciones.get(position);


        holder.barrio.setText(direccion.getBarrio());
        holder.direccion.setText(direccion.getDireccion());
        holder.nombreDireccion.setText(direccion.getNombreDireccion());
        holder.itemView.setTag(direccion);
        holder.propiedades = direccion;//paso la empresa aqui para pque en el onclick me reconosca q es esta empresa donde doy click
    }

    @Override
    public int getItemCount() {
        return direcciones.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreDireccion;
        public TextView direccion;
        public TextView barrio;
        public Direcciones propiedades;

        public ViewHolder(View itemView){
            super(itemView);
            nombreDireccion = (TextView) itemView.findViewById(R.id.txtNombreDireccion);
            direccion = (TextView) itemView.findViewById(R.id.txtDireccion);
            barrio = (TextView) itemView.findViewById(R.id.txtBarrio);


            //aqui capturo el click de cada cardview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(is_select==true){


                    }
                    else{
                        // Intent i = new Inten
                        Intent i = new Intent (activity, AddDirecciones.class);

                        i.putExtra("bandera",true);
                        i.putExtra("nombreDireccion", propiedades.getNombreDireccion());
                        i.putExtra("direccion", propiedades.getDireccion());
                        i.putExtra("barrio", propiedades.getBarrio());
                        i.putExtra("referencia", propiedades.getReferencia());
                        i.putExtra("id",propiedades.getId());

                        activity.startActivity(i);

                    }




                    // Toast.makeText(view.getContext(),"llave de consulta: "+ propiedades.getWebsafeKey(),Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}
