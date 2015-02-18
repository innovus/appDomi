package com.innovus.doomi.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.appspot.domi_app.domi.model.Empresa;
import com.innovus.doomi.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janeth Arcos on 17/02/2015.
 */
public class EmpresasAdapter extends RecyclerView.Adapter<EmpresasAdapter.ViewHolder> {
    private List<Empresa> empresas;//dataset
    private int itemLayout;//es la vista que va a cargar la row_empresas.xml


    public EmpresasAdapter(List<Empresa> empresas,int itemLayout){
        this.itemLayout = itemLayout;
        this.empresas = empresas;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);//infla el itemklayouy el row_empresas
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //lcargo el view con los valores
        Empresa empresa = empresas.get(position);
        holder.nombreEmpresa.setText(empresa.getNombre());
        holder.descripcionEmpresa.setText(empresa.getDescripcion());
        holder.minimosEmpresa.setText("Tiempo Minimo: "+empresa.getTiempoMinimo()+ " Min");
    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreEmpresa;
        public TextView descripcionEmpresa;
        public TextView minimosEmpresa;

        public ViewHolder(View itemView){
            super(itemView);

            nombreEmpresa = (TextView) itemView.findViewById(R.id.nombre);
            descripcionEmpresa = (TextView) itemView.findViewById(R.id.descripcion);
            minimosEmpresa = (TextView) itemView.findViewById(R.id.minimos);

        }
    }
}
